package com.treintaYTres.vdc.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.twotone.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.treintaYTres.vdc.ui.component.DateModal
import com.treintaYTres.vdc.ui.component.EventCard
import com.treintaYTres.vdc.ui.component.FAB
import com.treintaYTres.vdc.ui.component.FlowFilterChipGroup
import com.treintaYTres.vdc.ui.component.SegmentedButtonTopBar
import com.treintaYTres.vdc.ui.model.Action
import com.treintaYTres.vdc.ui.model.Chip
import com.treintaYTres.vdc.ui.model.localUiController

@Composable
fun EventScreen(
    navigateToEventDetailsScreen: (Int) -> Unit,
    navigateToNewEventScreen: () -> Unit
) {
    val uiController = localUiController.current

    val listState = rememberLazyListState()
    var showModal by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        uiController.topBarState.topBar = {
            SegmentedButtonTopBar(
                title = "Events",
                options = listOf(
                    Action.Text("Next") {

                    },
                    Action.Text("Previous") {

                    }
                ),
                actions = listOf(
                    Action.Icon.Vector(icon = Icons.TwoTone.DateRange) {
                        showModal = !showModal
                    }
                )
            )
        }
        uiController.fabState.fab = {
            FAB(
                listState = listState,
                icon = Icons.Rounded.Add,
                title = "New Event",
                onClick = navigateToNewEventScreen
            )
        }
    }

    Column {
        FlowFilterChipGroup(
            chips = listOf(
                Chip("All") {

                },
                Chip("Performances") {

                },
                Chip("Rehearsal") {

                },
            ),
            isFirstSelected = true
        )

        LazyColumn(
            modifier = Modifier.padding(horizontal = 26.dp),
            contentPadding = PaddingValues(horizontal = 26.dp, vertical = 16.dp),
            state = listState
        ) {
            items(emptyList<Int>()) {
                EventCard(
                    date = "",
                    title = "",
                    location = "",
                    confirmationState = 0
                ) {
                    //navigateToEventDetailsScreen(id)
                }
            }
        }
    }
    if (showModal) {
        DateModal(
            onDateSelected = {

            },
            onDismiss = {
                showModal = !showModal
            }
        )
    }

}