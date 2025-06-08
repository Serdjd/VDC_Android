package com.treintaYTres.vdc.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import com.treintaYTres.vdc.network.Result
import com.treintaYTres.vdc.ui.component.NewCard
import com.treintaYTres.vdc.ui.component.LogoTopBar
import com.treintaYTres.vdc.ui.component.MemberItem
import com.treintaYTres.vdc.ui.component.ModalSheet
import com.treintaYTres.vdc.ui.component.TextHeader
import com.treintaYTres.vdc.ui.model.band.BandInfo
import com.treintaYTres.vdc.ui.model.localUiController
import com.treintaYTres.vdc.ui.model.people.Instrument
import com.treintaYTres.vdc.viewmodel.BandViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BandScreen(
    viewModel: BandViewModel,
    stateHandle: SavedStateHandle?,
    navigateToInstruments: (List<Instrument>) -> Unit
) {
    val uiController = localUiController.current

    val data = viewModel.info.collectAsState()

    LaunchedEffect(Unit) {
        uiController.topBarState.topBar.value = {
            LogoTopBar()
        }
        uiController.clearFab()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        when (data.value) {
            is Result.Success<*> -> Info(
                viewModel,
                (data.value as Result.Success).data,
                stateHandle,
                navigateToInstruments
            )
            else -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Info(
    viewModel: BandViewModel,
    data: BandInfo,
    stateHandle: SavedStateHandle?,
    navigateToInstruments: (List<Instrument>) -> Unit
) {

    var showSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()

    var indexSelected by rememberSaveable { mutableIntStateOf(0) }

    LaunchedEffect(scope) {
        stateHandle
            ?.getStateFlow<List<Instrument>>(
                "instruments",
                listOf()
            )?.collectLatest {
                if (it.isNotEmpty()) {
                    data.members[indexSelected].instruments.value = it
                    viewModel.updateInstruments(data.members[indexSelected].id,it)
                }
            }
    }

    with(data) {
        Column(
            modifier = Modifier.padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextHeader("News")
            LazyRow(
                contentPadding = PaddingValues(horizontal = 6.dp)
            ) {
                items(news) {
                    NewCard(it, Color.Unspecified)
                }
            }
        }
        Column(
            modifier = Modifier.padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextHeader("Members")
            LazyColumn {
                itemsIndexed(members) { index, member ->
                    if (isAdmin) {
                        MemberItem(member) {
                            indexSelected = index
                            scope.launch {
                                sheetState.show()
                            }
                            showSheet = !showSheet
                        }
                    } else {
                        MemberItem(member)
                    }
                }
            }
        }

        if (showSheet) {
            ModalSheet(
                userId = viewModel.getUserId(),
                member = members[indexSelected],
                actions = listOf(
                    {
                        scope.launch {
                            sheetState.hide()
                            showSheet = !showSheet
                        }
                        viewModel.updatePermissions(members[indexSelected])
                    },
                    { navigateToInstruments.invoke(members[indexSelected].instruments.value) },
                    {
                        scope.launch {
                            val result = async {
                                viewModel.deleteMember(id = members[indexSelected].id)
                            }
                            result.await().let {
                                sheetState.hide()
                                delay(50)
                                viewModel.removeAtIndex(indexSelected)
                                showSheet = !showSheet
                            }
                        }
                    }
                ),
                sheetState = sheetState
            ) {
                showSheet = !showSheet
            }
        }
    }
}