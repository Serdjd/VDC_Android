package com.treintaYTres.vdc.ui.screen.eventdetails

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.treintaYTres.vdc.ui.component.AssistanceHeader
import com.treintaYTres.vdc.ui.component.AssistanceMemberItem
import com.treintaYTres.vdc.ui.component.PairedFilterChipGroup
import com.treintaYTres.vdc.ui.component.StringHeader
import com.treintaYTres.vdc.ui.model.Chip
import com.treintaYTres.vdc.ui.model.event.Detail
import com.treintaYTres.vdc.ui.model.event.Member
import com.treintaYTres.vdc.ui.model.people.StringAttendance
import com.treintaYTres.vdc.ui.util.Constant

@Composable
fun EventAttendanceScreen(
    details: List<Detail>
) {
    var selected by remember { mutableStateOf(true) }
    val scrollState = rememberScrollState()

    val onSelectedChange: ((Boolean) -> Unit) = {
        selected = it
    }

    val map = remember {
        mutableMapOf<Int, MutableList<Member>>(
            Constant.Event.POSITIVE_CONFIRMATION to mutableListOf(),
            Constant.Event.NEGATIVE_CONFIRMATION to mutableListOf(),
            Constant.Event.PENDING_CONFIRMATION to mutableListOf()
        )
    }

    LaunchedEffect(Unit) {
        details.forEach {
            it.members.forEach {
                map[it.attendance]?.add(it) ?: map.put(
                    it.attendance,
                    mutableListOf(it)
                )
            }
        }
    }

    val keys by remember { mutableStateOf(map.keys.toList()) }

    Column(
        modifier = Modifier.verticalScroll(scrollState).padding(top = 16.dp)
    ) {
        PairedFilterChipGroup(
            firstChip = Chip("Cuerdas") { onSelectedChange.invoke(true) },
            secondChip = Chip("Asistencia") { onSelectedChange.invoke(false) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        AnimatedContent(
            targetState = selected,
            transitionSpec = {
                if (selected) (slideInHorizontally { -it }).togetherWith(slideOutHorizontally { it })
                else (slideInHorizontally { it }).togetherWith(slideOutHorizontally { -it })
            }
        ) {
            if (it) Column {
                details.forEachIndexed { keyIndex, item ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        with(item.instrumentString) {
                            StringHeader(
                                StringAttendance(
                                    name,
                                    url ?: "",
                                    confirmed,
                                    cancelled
                                )
                            )
                        }

                        HorizontalDivider()

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            item.members.forEachIndexed { index, it ->
                                if (it.attendance != Constant.Event.PENDING_CONFIRMATION) {
                                    AssistanceMemberItem(it)

                                    if (index < item.members.size - 1 || keyIndex < details.size - 1)
                                        HorizontalDivider()
                                }
                            }
                        }
                    }
                }
            }
            else Column {
                keys.forEachIndexed { keyIndex, item ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val items = map[item]!!

                        AssistanceHeader(item, items.size)

                        HorizontalDivider()


                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            items.forEachIndexed { index, it ->
                                AssistanceMemberItem(it)
                                if (index < items.size - 1 || keyIndex < keys.size - 1)
                                    HorizontalDivider()
                            }
                        }
                    }
                }
            }
        }
    }

}