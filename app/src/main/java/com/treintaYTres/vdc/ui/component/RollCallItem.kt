package com.treintaYTres.vdc.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import com.google.android.play.integrity.internal.b
import com.treintaYTres.vdc.R
import com.treintaYTres.vdc.ui.model.create.Group
import com.treintaYTres.vdc.ui.model.people.Instrument
import com.treintaYTres.vdc.ui.model.people.Person
import com.treintaYTres.vdc.ui.model.rollcall.RollCallItem
import com.treintaYTres.vdc.ui.theme.VdcTheme

@Composable
fun RollCallItem(
    data: RollCallItem
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Profile(data.url, 64)
            UserInfo(data.name, data.instrument)
        }

        Checkbox(
            checked = data.attendance.value,
            onCheckedChange = {
                data.attendance.value = it
            }
        )
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview(
    showBackground = true
)
@Composable
fun RollCallItemPrev() {
    val provider = AsyncImagePreviewHandler {
        ColorImage(Color.DarkGray.toArgb())
    }
    VdcTheme {
        Surface {
            CompositionLocalProvider(LocalAsyncImagePreviewHandler provides provider) {
                RollCallItem(
                    RollCallItem(
                        remember { mutableStateOf(false) },
                        0,
                        instrument = Instrument(
                            0,
                            "Clarinet",
                            ""
                        ),
                        "Sergio Doblado Muñoz",
                        ""
                    )
                )
            }
        }
    }
}

@Composable
fun GroupItem(
    group: Group,
    onCheckedChange: (Boolean) -> Unit
) {
    var checked = remember { derivedStateOf { group.selected.value } }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Profile(group.url, 64)
            TextTitleMedium(group.name)
        }

        Checkbox(
            checked = checked.value,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
fun GroupItem(
    icon: Int,
    name: String,
    selected: MutableState<Boolean>,
    onCheckedChange: (Boolean) -> Unit
) {
    var checked by remember { mutableStateOf(selected) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Profile(icon, 48)
            TextTitleMedium(name)
        }

        Checkbox(
            checked = checked.value,
            onCheckedChange = onCheckedChange
        )
    }
}

@Preview
@Composable
fun GroupItemPrev() {
    VdcTheme {
        Column {
            Surface {
                GroupItem(
                    R.drawable.all,
                    "Todos",
                    remember { mutableStateOf(false) }
                ) {

                }
            }
        }
    }
}