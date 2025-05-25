package com.treintaYTres.vdc.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.treintaYTres.vdc.ui.component.InfoCard
import com.treintaYTres.vdc.ui.component.LogoTopBar
import com.treintaYTres.vdc.ui.component.MemberItem
import com.treintaYTres.vdc.ui.component.ModalSheet
import com.treintaYTres.vdc.ui.component.Sheet
import com.treintaYTres.vdc.ui.component.TextHeader
import com.treintaYTres.vdc.ui.model.New
import com.treintaYTres.vdc.ui.model.localUiController
import com.treintaYTres.vdc.ui.model.people.Person
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BandScreen(
    isAdmin: Boolean,
    news: List<New>,
    members: List<Person>
) {
    val uiController = localUiController.current
    var showSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    val scrollState = rememberScrollState()

    var indexSelected by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        uiController.topBarState.topBar = {
            LogoTopBar()
        }
        uiController.clearFab()
    }

    Column(
        modifier = Modifier.verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Column(
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            TextHeader("News")
            LazyRow(
                contentPadding = PaddingValues(horizontal = 6.dp)
            ) {
                items(news) {
                    InfoCard(it)
                }
            }
        }
        Column(
            modifier = Modifier.padding(horizontal = 10.dp)
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
    }
    if (showSheet) {
        ModalSheet(
            member = members[indexSelected],
            actions = listOf(),
            sheetState = sheetState
        ) {
            showSheet = !showSheet
        }
    }
}