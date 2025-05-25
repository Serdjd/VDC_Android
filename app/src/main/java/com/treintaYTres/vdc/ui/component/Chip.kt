package com.treintaYTres.vdc.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ChipColors
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.treintaYTres.vdc.ui.model.Action
import com.treintaYTres.vdc.ui.theme.VdcTheme

@Composable
fun FilterChip(
    modifier: Modifier = Modifier,
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
        shape = CircleShape
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
        onClick = onClick,
        label = { Text(title) },
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = "Chip Icon"
            )
        },
        enabled = false,
        colors = chipColors,
        border = borderStroke
    )
}
