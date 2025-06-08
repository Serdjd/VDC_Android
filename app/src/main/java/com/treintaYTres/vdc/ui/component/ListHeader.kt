package com.treintaYTres.vdc.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.treintaYTres.vdc.ui.model.Icon.VectorIcon
import com.treintaYTres.vdc.ui.model.people.StringAttendance
import com.treintaYTres.vdc.ui.theme.VdcTheme
import com.treintaYTres.vdc.ui.util.Constant

@Composable
fun StringHeader(
    stringAttendance: StringAttendance
) {
    Surface(
        color = MaterialTheme.colorScheme.outlineVariant
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(stringAttendance.instrumentUrl, Modifier.size(24.dp))
                Text(text = stringAttendance.stringName, style = MaterialTheme.typography.titleMedium)
            }
            AssistanceAmount(stringAttendance.confirmed, stringAttendance.cancelled)
        }
    }

}

@Preview(
    showBackground = true
)
@Composable
fun StringHeaderPrev() {
    VdcTheme {
        StringHeader(StringAttendance(
            "Clarinet",
            "",
            1,
            2)
        )
    }
}

@Composable
fun AssistanceHeader(
    state: Int,
    quantity: Int
) {
    var icon = VectorIcon(Icons.Outlined.Notifications, "State")
    var text = "Pendientes"
    when (state) {

        Constant.Event.POSITIVE_CONFIRMATION -> {
            icon.resource = Icons.Rounded.Check
            text = "Irá"
        }

        Constant.Event.NEGATIVE_CONFIRMATION  -> {
            icon.resource = Icons.Rounded.Close
            text = "No Irá"
        }
    }
    Surface(
        color = MaterialTheme.colorScheme.outlineVariant
    ) {
        SimpleItem(
            icon = icon,
            text = "$quantity $text",
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            gap = 16,
            iconSize = 24,
            textStyle = MaterialTheme.typography.titleMedium,
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun AssistanceHeaderPrev() {
    VdcTheme {
        AssistanceHeader(2, 2)
    }
}