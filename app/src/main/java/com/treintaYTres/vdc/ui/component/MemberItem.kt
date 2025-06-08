package com.treintaYTres.vdc.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.treintaYTres.vdc.ui.model.event.Member
import com.treintaYTres.vdc.ui.theme.VdcTheme

@Composable
fun MemberItem(
    member: Member,
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
            Profile(member.url, 64, isAdministrator = member.isAdmin.value)
            UserInfo(member.name, member.instruments.value[0])
        }
        Surface(
            modifier = Modifier.size(30.dp),
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
    member: Member
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
        Profile(member.url, 64, isAdministrator = member.isAdmin.value)
            UserInfo(member.name, member.instruments.value[0])
        }
    }
}

@Composable
fun SheetMemberItem(
    member: Member
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
            Profile(member.url, 128)
            UserInfoSheet(member.name, member.instruments.value[0])
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
                    Member(
                        0,
                        1,
                        remember { mutableStateOf(listOf()) },
                        remember { mutableStateOf(true) },
                        "Sergio Doblado Muñoz",
                        false,
                        ""
                    )
                ) {}
            }
        }
    }

}

@Composable
fun AssistanceMemberItem(
    member: Member
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                ProfileState(member.url, 48, member.attendance)
                Text(text = member.name, style = MaterialTheme.typography.titleMedium)
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(member.instruments.value[0].url)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(16.dp)
                )
                Text(text = member.instruments.value[0].name, style = MaterialTheme.typography.labelMedium)
            }
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
                    Member(
                        0,
                        1,
                        remember { mutableStateOf(listOf()) },
                        remember { mutableStateOf(false) },
                        "Sergio Doblado Muñoz",
                        false,
                        ""
                    )
                )
            }
        }
    }

}