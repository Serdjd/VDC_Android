package com.treintaYTres.vdc.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import com.treintaYTres.vdc.ui.model.people.Instrument
import com.treintaYTres.vdc.ui.model.people.Person
import com.treintaYTres.vdc.ui.theme.VdcTheme

@Composable
fun RollCallItem(
    person: Person,
    onCheckedChange: (Boolean) -> Unit
) {
    var checked by remember { mutableStateOf(false) }
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
            Profile(person.url, 64, person.isAdministrator)
            person.instrument?.let {
                UserInfo(person.name, it)
            }
        }

        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
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
                    Person(
                        0,
                        "",
                        "Sergio Doblado Muñoz",
                        instrument = Instrument(
                            0,
                            "Clarinet",
                            ""
                        )
                    )
                ) {}
            }
        }
    }

}
