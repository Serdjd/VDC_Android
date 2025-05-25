package com.treintaYTres.vdc.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.treintaYTres.vdc.ui.component.InstrumentCard
import com.treintaYTres.vdc.ui.component.OutlinedTextFieldTopBar
import com.treintaYTres.vdc.ui.model.Action
import com.treintaYTres.vdc.ui.model.NavIcon
import com.treintaYTres.vdc.ui.model.people.Instrument

@Composable
fun SelectInstrumentScreen(
    selectedInstruments: List<Instrument>,
    instruments: List<Instrument>
) {
    val instrumentsState = remember{ mutableStateListOf(*instruments.toTypedArray()) }
    val selectedInstrumentsState = remember{ mutableStateListOf(*selectedInstruments.toTypedArray()) }
    var filter by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            OutlinedTextFieldTopBar(
                "Choose an Instrument",
                "Find an Instrument",
                filter,
                selectedInstrumentsState,
                Icons.Rounded.Search,
                navIcon = NavIcon.Back(Icons.AutoMirrored.Rounded.ArrowBack) {

                },
                actions = listOf(
                    Action.Text("Save") {

                    }
                )
            ) {
                filter = it
            }
        }
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(it),
            columns = GridCells.Fixed(3)
        ) {
            itemsIndexed(instrumentsState) { index,instrument ->
                InstrumentCard(
                    url = instrument.url,
                    isSelected = selectedInstruments.find { it.id == instrument.id }?.let { true } == true,
                    isPrimary = index == 0
                ) {
                    if(it) {
                        selectedInstrumentsState.add(instrumentsState[index])
                    } else {
                        selectedInstrumentsState.remove(
                            selectedInstruments.find { it.id == instrument.id }
                        )
                    }
                }
            }
        }
    }
}