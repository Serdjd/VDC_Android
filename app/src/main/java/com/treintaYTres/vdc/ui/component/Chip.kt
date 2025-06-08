package com.treintaYTres.vdc.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.ChipColors
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.treintaYTres.vdc.ui.model.Action
import com.treintaYTres.vdc.ui.model.Icon
import com.treintaYTres.vdc.ui.theme.VdcTheme

@Composable
fun FilterChip(
    modifier: Modifier = Modifier,
    icon: Icon? = null,
    title: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        modifier = modifier,
        selected = selected,
        onClick = { onClick.invoke() },
        label = {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                Text(text = title)
            }

        },
        leadingIcon = {
            icon?.let {
                when(it) {
                    is Icon.VectorIcon -> Icon(
                        imageVector = it.resource,
                        contentDescription = it.contentDescription
                    )
                    is Icon.DrawableIcon -> Icon(
                        painter = painterResource(it.resource),
                        contentDescription = it.contentDescription
                    )
                }
            }
        },
        shape = CircleShape
    )
}

@Composable
fun FilterChip(
    modifier: Modifier = Modifier,
    url: String,
    title: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        modifier = modifier,
        selected = selected,
        onClick = { onClick.invoke() },
        label = {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                Text(text = title)
            }

        },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = rememberAsyncImagePainter(
                    model = url,
                    contentScale = ContentScale.Crop
                ),
                contentDescription = null
            )
        }
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun FilterChipPrev() {
    VdcTheme {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = 12.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            FilterChip(
                icon = Icon.VectorIcon(Icons.Rounded.Person),
                title = "All",
                selected = true
            ) {}
            FilterChip(
                title = "Performances",
                selected = false
            ) {}
            FilterChip(
                title = "Rehearsal",
                selected = false
            ) {}
        }

    }
}

@Composable
fun AssistChip(
    title: String,
    icon: ImageVector,
    chipColors: ChipColors,
    borderStroke: BorderStroke,
    onClick: () -> Unit
) {
    SuggestionChip(
        onClick = { onClick() },
        label = { Text(title) },
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = "Chip Icon"
            )
        },
        colors = chipColors,
        border = borderStroke
    )
}
