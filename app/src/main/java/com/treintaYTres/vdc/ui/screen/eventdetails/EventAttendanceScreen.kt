package com.treintaYTres.vdc.ui.screen.eventdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.treintaYTres.vdc.ui.component.AssistanceHeader
import com.treintaYTres.vdc.ui.component.AssistanceMemberItem
import com.treintaYTres.vdc.ui.component.PairedFilterChipGroup
import com.treintaYTres.vdc.ui.component.StringHeader
import com.treintaYTres.vdc.ui.model.Chip
import com.treintaYTres.vdc.ui.model.attendance.Attendance
import com.treintaYTres.vdc.ui.model.people.Person
import com.treintaYTres.vdc.ui.model.people.StringAttendance
import kotlin.collections.mutableListOf

@Composable
fun EventAttendanceScreen(
    values: Map<StringAttendance, List<Person>>
) {
    var selected by remember { mutableStateOf(true) }

    val onSelectedChange: ((Boolean) -> Unit) = {
        selected = it
    }

    Column {
        PairedFilterChipGroup(
            firstChip = Chip("Cuerdas") {onSelectedChange.invoke(true)},
            secondChip = Chip("Asistencia") {onSelectedChange.invoke(false)}
        )
        if (selected) {
            val keys by remember { mutableStateOf(values.keys.toList()) }
            LazyColumn {
                itemsIndexed(keys) { keyIndex, item ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        StringHeader(item)

                        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

                        val items = values[item]!!
                        LazyColumn(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            itemsIndexed(items = items) { index, it ->
                                AssistanceMemberItem(it)
                                if (index < items.size - 1 || keyIndex < keys.size - 1)
                                    HorizontalDivider(
                                        modifier = Modifier.padding(16.dp)
                                    )
                            }
                        }
                    }
                }
            }
        }
        else {
            val map = remember {
                mutableMapOf<Int, MutableList<Person>>().apply {
                    values.forEach {
                        it.value.forEach {
                            this[it.assistance]?.add(it) ?: this.put(it.assistance,mutableListOf(it))
                        }
                    }
                }
            }

            val keys by remember { mutableStateOf(map.keys.toList()) }

            LazyColumn {
                itemsIndexed(keys) { keyIndex, item ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        AssistanceHeader(item,map[item]!!.size)

                        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

                        val items = map[item]!!
                        LazyColumn(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            itemsIndexed(items = items) { index, it ->
                                AssistanceMemberItem(it)
                                if (index < items.size - 1 || keyIndex < keys.size - 1)
                                    HorizontalDivider(
                                        modifier = Modifier.padding(16.dp)
                                    )
                            }
                        }
                    }
                }
            }
        }
    }

}