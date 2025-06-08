package com.treintaYTres.vdc.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.treintaYTres.vdc.network.Result
import com.treintaYTres.vdc.ui.component.InstrumentCard
import com.treintaYTres.vdc.ui.component.InstrumentIcon
import com.treintaYTres.vdc.ui.component.OutlinedTextFieldTopBar
import com.treintaYTres.vdc.ui.model.Action
import com.treintaYTres.vdc.ui.model.NavIcon
import com.treintaYTres.vdc.ui.model.people.Instrument
import com.treintaYTres.vdc.viewmodel.InstrumentsViewModel

@Composable
fun SelectInstrumentScreen(
    viewModel: InstrumentsViewModel,
    selectedInstruments: List<Instrument>,
    navigateBack: () -> Unit,
    backToInvocation: (List<Instrument>) -> Unit
) {
    val instrumentsState = viewModel.instruments.collectAsState()
    val selectedInstrumentsState =
        remember { mutableStateListOf(*selectedInstruments.toTypedArray()) }
    val primaryInstrument = remember {
        derivedStateOf {
            if (selectedInstrumentsState.isNotEmpty()) selectedInstrumentsState.first().id
            else -1
        }
    }
    var filter by remember { mutableStateOf("") }

    when (instrumentsState.value) {
        is Result.Loading<*> -> LoadingScreen()
        is Result.Success<*> -> with((instrumentsState.value as Result.Success).data) {
            Scaffold(
                topBar = {
                    OutlinedTextFieldTopBar(
                        "Choose an Instrument",
                        "Find an Instrument",
                        filter,
                        selectedInstrumentsState,
                        Icons.Rounded.Search,
                        navIcon = NavIcon.Back(Icons.AutoMirrored.Rounded.ArrowBack) {
                            navigateBack.invoke()
                        },
                        actions = listOf(
                            Action.Text("Save") {
                                val list = listOf(*selectedInstrumentsState.toTypedArray())
                                backToInvocation(list)
                            }
                        )
                    ) {
                        filter = it
                    }
                }
            ) {
                Column(
                    modifier = Modifier.padding(it)
                ) {
                    Surface(
                        modifier = Modifier.fillMaxWidth().heightIn(64.dp),
                        color = MaterialTheme.colorScheme.surface
                    ) {
                        if (selectedInstrumentsState.isEmpty()) {
                            Spacer(modifier = Modifier.height(64.dp))
                        } else {
                            LazyRow(
                                modifier = Modifier.fillMaxWidth().height(64.dp),
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                itemsIndexed(selectedInstrumentsState) { index, instrument ->
                                    InstrumentIcon(
                                        model = instrument.url,
                                        isPrimary = index == 0
                                    )
                                }
                            }
                        }
                    }
                    LazyVerticalGrid(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.onPrimary)
                            .padding(vertical = 8.dp),
                        columns = GridCells.Fixed(3),
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        itemsIndexed(this@with) { index, instrument ->
                            InstrumentCard(
                                url = instrument.url,
                                isSelected = selectedInstrumentsState.find { it.id == instrument.id }
                                    ?.let { true } == true,
                                isPrimary = instrument.id == primaryInstrument.value
                            ) {
                                if (!it) {
                                    selectedInstrumentsState.add(this@with[index])
                                } else {
                                    selectedInstrumentsState.remove(
                                        selectedInstrumentsState.find { it.id == instrument.id }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        is Result.Error<*> -> {}
    }
}

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}