package com.treintaYTres.vdc.ui.screen

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import com.treintaYTres.vdc.network.Result
import com.treintaYTres.vdc.network.model.types.EventFilter
import com.treintaYTres.vdc.network.model.types.EventType
import com.treintaYTres.vdc.ui.component.EventCard
import com.treintaYTres.vdc.ui.component.FAB
import com.treintaYTres.vdc.ui.component.FlowFilterChipGroup
import com.treintaYTres.vdc.ui.component.SegmentedButtonTopBar
import com.treintaYTres.vdc.ui.mapper.toInt
import com.treintaYTres.vdc.ui.model.Action
import com.treintaYTres.vdc.ui.model.Chip
import com.treintaYTres.vdc.ui.model.TypeE
import com.treintaYTres.vdc.ui.model.localUiController
import com.treintaYTres.vdc.viewmodel.EventViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun EventScreen(
    viewModel: EventViewModel,
    stateHandle: SavedStateHandle?,
    navigateToEventDetailsScreen: (Int, String, Boolean) -> Unit,
    navigateToNewEventScreen: () -> Unit
) {
    val uiController = localUiController.current

    val listState = rememberLazyListState()

    val events = viewModel.events.collectAsState(Result.Loading())

    LaunchedEffect(Unit) {
        uiController.topBarState.topBar.value = {
            SegmentedButtonTopBar(
                title = "Events",
                options = listOf(
                    Action.Text("Next") {
                        viewModel.getEvents(EventFilter.FUTURE)
                    },
                    Action.Text("Previous") {
                        viewModel.getEvents(EventFilter.PAST)
                    }
                )
            )
        }
        if (viewModel.isAdmin()) {
            uiController.fabState.fab.value = {
                FAB(
                    listState = listState,
                    icon = Icons.Rounded.Add,
                    title = "New Event",
                    onClick = {
                        navigateToNewEventScreen.invoke()
                    }
                )
            }
        }

        stateHandle
            ?.getStateFlow<Boolean>(
                "update",
                false
            )?.collectLatest {
                if (it) {
                    viewModel.changeType(EventType.ALL)
                    viewModel.getEvents(EventFilter.FUTURE)
                }
            }
    }

    Column {
        FlowFilterChipGroup(
            chips = listOf(
                Chip("All") {
                    viewModel.changeType(EventType.ALL)
                },
                Chip("Performances") {
                    viewModel.changeType(EventType.PERFORMANCES)
                },
                Chip("Rehearsal") {
                    viewModel.changeType(EventType.REHEARSAL)
                },
            ),
            isFirstSelected = true
        )
        when (events.value) {
            is Result.Success -> {
                events.value.data?.let {
                    LazyColumn(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        contentPadding = PaddingValues(
                            top = 8.dp,
                            bottom = 92.dp
                        ),
                        state = listState,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(it) {
                            EventCard(
                                date = it.date,
                                title = it.title,
                                location = it.location,
                                finished = viewModel.selectedFilter == EventFilter.PAST,
                                confirmationState = it.confirmationState.value,
                                confirmationClick = { attendance ->
                                    it.confirmationState.value = attendance.toInt()
                                    viewModel.updateAttendance(
                                        it.id,
                                        attendance
                                    )
                                },
                            ) {
                                navigateToEventDetailsScreen(it.id, TypeE.entries[it.type].name, viewModel.isAdmin())
                            }
                        }
                    }
                }
            }

            else -> {

            }
        }

    }

}