package com.treintaYTres.vdc.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
fun MemberItem(
    member: Person,
    onOptionsClick: () -> Unit
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
            Profile(member.url, 64, member.isAdministrator)
            UserInfo(member.name, member.instrument)
        }
        Surface(
            color = MaterialTheme.colorScheme.primaryContainer,
            shape = CircleShape
        ) {
            IconButton(onClick = onOptionsClick) {
                Icon(
                    imageVector = Icons.Rounded.MoreVert,
                    contentDescription = "More Options"
                )
            }
        }
    }
}

@Composable
fun MemberItem(
    member: Person
) {
    Row(
        modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Profile(member.url, 64, member.isAdministrator)
            UserInfo(member.name, member.instrument)
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview(
    showBackground = true
)
@Composable
fun MemberItemPrev() {
    val provider = AsyncImagePreviewHandler {
        ColorImage(Color.DarkGray.toArgb())
    }
    VdcTheme {
        Surface {
            CompositionLocalProvider(LocalAsyncImagePreviewHandler provides provider) {
                MemberItem(
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

@Composable
fun AssistanceMemberItem(
    member: Person
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
            ProfileState(member.url, 64, member.assistance)
            Text(text = member.name, style = MaterialTheme.typography.titleMedium)
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview(
    showBackground = true
)
@Composable
fun AssistanceMemberItemPrev() {
    val provider = AsyncImagePreviewHandler {
        ColorImage(Color.DarkGray.toArgb())
    }
    VdcTheme {
        Surface {
            CompositionLocalProvider(LocalAsyncImagePreviewHandler provides provider) {
                AssistanceMemberItem(
                    Person(0,"","Sergio Doblado Muñoz",0)
                )
            }
        }
    }

}