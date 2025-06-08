package com.treintaYTres.vdc.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.treintaYTres.vdc.network.Result
import com.treintaYTres.vdc.ui.component.BasicCreateEventCard
import com.treintaYTres.vdc.ui.component.DateCard
import com.treintaYTres.vdc.ui.component.GroupsCard
import com.treintaYTres.vdc.ui.component.TopBar
import com.treintaYTres.vdc.ui.mapper.toTypeChips
import com.treintaYTres.vdc.ui.model.Action
import com.treintaYTres.vdc.ui.model.NavIcon
import com.treintaYTres.vdc.ui.model.TypeE
import com.treintaYTres.vdc.ui.model.create.EventToCreate
import com.treintaYTres.vdc.ui.theme.VdcTheme
import com.treintaYTres.vdc.ui.util.Constant
import com.treintaYTres.vdc.viewmodel.CreateEventViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CreateEventScreen(
    viewModel: CreateEventViewModel,
    navigateBack: () -> Unit,
    navigateToEvents: () -> Unit
) {

    val groups = viewModel.groups.collectAsState()
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    val request by remember { mutableStateOf(EventToCreate()) }

    LaunchedEffect(scope) {
        viewModel.result.collectLatest {
            when (it) {
                is Result.Success<*> -> navigateToEvents.invoke()
                else -> {}
            }
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "Nuevo Evento",
                navIcon = NavIcon.Back(Icons.AutoMirrored.Rounded.ArrowBack, navigateBack),
                actions = listOf(
                    Action.Text("Guardar") {
                        viewModel.saveEvent(request)
                    }
                )
            )
        }
    ) {
        with(groups.value) {
            if (this is Result.Success<*>) with((this as Result.Success).data) {
                val groups = this
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = it.calculateTopPadding(),
                            bottom = it.calculateBottomPadding(),
                            start = 8.dp,
                            end = 8.dp,
                        )
                        .verticalScroll(scrollState),
                    verticalArrangement = Arrangement.spacedBy(32.dp)
                ) {
                    BasicCreateEventCard(
                        Constant.Events.ALL.toTypeChips {
                            request.type.value = TypeE.valueOf(it).ordinal
                        },
                        request.title,
                        request.comments
                    )

                    DateCard(
                        {
                            request.date.value = it
                        },
                        {
                            request.time.value = it
                        },
                        request.location,
                    )

                    GroupsCard(
                        groups,
                        request.instrumentStringIds
                    )
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun CreateEventPrev() {
    VdcTheme {
        CreateEventScreen(
            hiltViewModel(),
            {
            }
        ) {}
    }
}