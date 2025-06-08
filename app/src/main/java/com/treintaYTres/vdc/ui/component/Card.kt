package com.treintaYTres.vdc.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.treintaYTres.vdc.R
import com.treintaYTres.vdc.ui.mapper.toTypeChips
import com.treintaYTres.vdc.ui.model.Action
import com.treintaYTres.vdc.ui.model.Chip
import com.treintaYTres.vdc.ui.model.Header
import com.treintaYTres.vdc.ui.model.Icon
import com.treintaYTres.vdc.ui.model.Icon.DrawableIcon
import com.treintaYTres.vdc.ui.model.New
import com.treintaYTres.vdc.ui.model.RowInfo
import com.treintaYTres.vdc.ui.model.create.Group
import com.treintaYTres.vdc.ui.model.profile.Stat
import com.treintaYTres.vdc.ui.model.profile.Type
import com.treintaYTres.vdc.ui.theme.VdcTheme
import com.treintaYTres.vdc.ui.theme.textThin
import com.treintaYTres.vdc.ui.util.Constant

@Composable
fun EventCard(
    date: String,
    title: String,
    location: String,
    confirmationState: Int,
    finished: Boolean,
    confirmationClick: (Boolean) -> Unit,
    onClick: () -> Unit
) {

    ElevatedCard(
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = date,
                style = MaterialTheme.typography.titleMedium
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.location_on),
                        contentDescription = "Location Icon"
                    )
                    Text(
                        text = location,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {

                when (confirmationState) {
                    Constant.Event.PENDING_CONFIRMATION -> {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (finished) {
                                EventFinished()
                            } else {
                                Text(
                                    text = "Confirm your attendance",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = textThin
                                )
                                PendingConfirm(onClick = confirmationClick)
                            }

                        }
                    }

                    Constant.Event.POSITIVE_CONFIRMATION -> {
                        if (finished) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                ) {
                                    EventFinished()
                                }
                                PositiveConfirmation {}
                            }
                        } else {
                            PositiveConfirmation {
                                confirmationClick(!it)
                            }
                        }
                    }

                    Constant.Event.NEGATIVE_CONFIRMATION -> {
                        if (finished) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                ) {
                                    EventFinished()
                                }
                                NegativeConfirmation {}
                            }
                        } else {
                            NegativeConfirmation {
                                confirmationClick(!it)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun EventCardPrev() {
    VdcTheme {
        EventCard(
            date = "Tomorrow at 1",
            title = "Ensayo",
            location = "Silo de trigo",
            confirmationState = Constant.Event.NEGATIVE_CONFIRMATION,
            true,
            {}
        ) {

        }
    }
}

@Composable
fun InstrumentCard(
    url: String,
    isSelected: Boolean = false,
    isPrimary: Boolean = false,
    onClick: (Boolean) -> Unit
) {

    val color = if (isPrimary) {
        MaterialTheme.colorScheme.inversePrimary
    } else if (isSelected) {
        MaterialTheme.colorScheme.outline
    } else Color.Transparent

    ElevatedCard(onClick = { onClick(isSelected) }) {
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .border(
                    2.dp,
                    color,
                    CardDefaults.elevatedShape
                )
                .padding(20.dp)
        ) {
            Image(
                url = url,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun ListCard(
    header: Header,
    items: List<RowInfo>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        SimpleItem(header.icon, header.title)
        ElevatedCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                items.forEachIndexed { index, item ->
                    Box(
                        modifier = Modifier.height(60.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        with(item) {
                            action?.let {
                                SimpleItemClickable(
                                    icon,
                                    text,
                                    it,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            } ?: SimpleItem(icon, text, modifier = Modifier.fillMaxWidth())
                        }

                        if (index < items.size - 1)
                            HorizontalDivider(
                                modifier = Modifier.align(alignment = Alignment.BottomCenter)
                            )
                    }
                }

            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ListCardPreview() {
    val header = Header(
        title = "Opciones",
        icon = DrawableIcon(
            resource = android.R.drawable.ic_menu_info_details,
            contentDescription = "Info"
        )
    )

    val items = listOf(
        RowInfo(
            icon = DrawableIcon(
                resource = android.R.drawable.ic_menu_call,
                contentDescription = "Llamar"
            ),
            text = "Llamar",
            action = Action.Icon.Drawable(
                icon = android.R.drawable.ic_menu_call,
                action = { }
            )
        ),
        RowInfo(
            icon = DrawableIcon(
                resource = android.R.drawable.ic_menu_camera,
                contentDescription = "Cámara"
            ),
            text = "Abrir cámara"
        ),
        RowInfo(
            icon = DrawableIcon(
                resource = android.R.drawable.ic_menu_compass,
                contentDescription = "Navegar"
            ),
            text = "Navegar",
            action = Action.Icon.Drawable(
                icon = android.R.drawable.ic_menu_compass,
                action = { }
            )
        )
    )
    VdcTheme {
        ListCard(header = header, items = items)
    }
}

@Composable
fun NewCard(
    new: New,
    color: Color = LocalContentColor.current
) {
    val maxWith = (LocalConfiguration.current.screenWidthDp - 32).dp
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .requiredHeight(240.dp)
                .requiredWidth(maxWith)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            with(new) {
                header?.let {
                    SimpleItem(
                        icon = it.icon,
                        text = it.title,
                        iconSize = 40,
                        textStyle = MaterialTheme.typography.titleMedium,
                        color = color
                    )
                }

                TextBody(text = text)

                date?.let {
                    SimpleItem(
                        icon = DrawableIcon(R.drawable.schedule),
                        text = it,
                        modifier = Modifier.align(Alignment.End),
                        iconSize = 16,
                        textStyle = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Composable
fun InfoCard(
    new: New,
    color: Color = LocalContentColor.current
) {
    val maxWith = (LocalConfiguration.current.screenWidthDp - 32).dp

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        with(new) {
            header?.let {
                SimpleItem(
                    icon = it.icon,
                    text = it.title,
                    iconSize = 24,
                    textStyle = MaterialTheme.typography.titleMedium,
                    color = color
                )
            }
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 240.dp)
                    .requiredWidth(maxWith)
            ) {
                Box(
                    modifier = Modifier.padding(16.dp)
                ) {
                    TextBody(text = text)
                }
            }
        }
    }
}

@Composable
fun StatCard(
    stat: Stat
) {
    val percentage = if (stat.total != 0) {
        ((stat.assistance.toFloat() / stat.total.toFloat()) * 100).toInt()
    } else 0

    val data = if (percentage > 0) "$percentage %" else "-"

    ElevatedCard {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .aspectRatio(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            SimpleItem(
                icon = stat.type.icon,
                text = stat.type.name,
                textStyle = MaterialTheme.typography.titleMedium
            )
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        TextTitleMedium(
                            text = data
                        )
                        Text(
                            text = "asistencia",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    TextTitleMedium(
                        text = "${stat.assistance}/${stat.total}"
                    )
                }
                LinearProgressIndicator(
                    progress = { (percentage.toFloat() / 100) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp),
                    gapSize = -(15).dp
                )
            }

        }
    }
}

@Preview
@Composable
fun StatCardPrev() {
    VdcTheme {
        StatCard(
            Stat(
                1,
                2,
                Type("Performance", Icon.DrawableIcon(R.drawable.performance))
            )
        )
    }
}

@Composable
fun BasicCreateEventCard(
    types: List<Chip>,
    title: MutableState<String>,
    comment: MutableState<String>,
) {

    val scrollState = rememberScrollState()
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextTitleMedium("Tipo de evento")
            ScrollableFilterChipGroup(types, true)
            TextTitleMedium("Información Básica")
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                state = title.value,
                placeholder = "Título"
            ) {
                title.value = it
            }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(200.dp)
                    .verticalScroll(scrollState),
                state = comment.value,
                placeholder = "Comentarios",
                singleLine = false
            ) {
                comment.value = it
            }
        }
    }
}

@Preview
@Composable
fun BasicCreateEventCardPrev() {
    VdcTheme {
        BasicCreateEventCard(
            listOf(
                Type("Rehearsal", Icon.DrawableIcon(R.drawable.rehearsal)),
                Type("Performance", Icon.DrawableIcon(R.drawable.performance))
            ).toTypeChips({ it }),
            remember { mutableStateOf("") },
            remember { mutableStateOf("") }
        )
    }
}

@Composable
fun DateCard(
    onDateChange: (String) -> Unit,
    onTimeChange: (String) -> Unit,
    location: MutableState<String>
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextTitleMedium("Fecha, Hora y Ubicación")

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                DateInput { onDateChange(it) }

                TimeInput { onTimeChange(it) }

                OutlinedTextField(
                    modifier = Modifier.requiredWidth(OutlinedTextFieldDefaults.MinWidth),
                    location.value,
                    "Ubicación",
                    icon = Icons.Rounded.LocationOn
                ) {
                    location.value = it
                }
            }
        }
    }
}

@Preview
@Composable
fun DateCardPrev() {
    VdcTheme {
        DateCard(
            {},
            {},
            remember { mutableStateOf("") },
        )
    }
}

@Composable
fun GroupsCard(
    list: List<Group>,
    result: MutableList<Int>
) {
    var allSelected = remember { mutableStateOf(false) }
    var groups = remember { mutableListOf(*list.toTypedArray()) }

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            GroupItem(
                R.drawable.all,
                "Todos",
                allSelected
            ) {
                if (it) {
                    groups.filter { it.selected.value }.forEach { it.selected.value = false }
                    result.clear()
                    result.addAll(groups.map { it.id })
                    groups.forEach { it.selected.value = true }
                } else if (allSelected.value) {
                      result.clear()
                      groups.forEach { it.selected.value = false }
                }
                allSelected.value = it
            }
            HorizontalDivider()
            groups.forEachIndexed { index, group ->
                GroupItem(group) {
                    if (it) result.add(group.id)
                    else {
                        result.remove(group.id)
                        if (allSelected.value) allSelected.value = false
                    }
                    group.selected.value = it
                }
                if (index < groups.size - 1) HorizontalDivider()
            }
        }
    }

}

@Preview
@Composable
fun GroupsCardPrev() {
    VdcTheme {
        GroupsCard(
            listOf(
                Group(0, "Clarinetes", ""),
                Group(1, "Tambores", ""),
                Group(2, "Clave de Fa", ""),
                Group(3, "Saxofónes", ""),
                Group(4, "Flautas", ""),
            ),
            mutableListOf()
        )
    }
}
