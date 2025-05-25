package com.treintaYTres.vdc.ui.component

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.treintaYTres.vdc.ui.util.isScrollingUp

@Composable
fun FAB(
    listState: LazyListState? = null,
    icon: ImageVector,
    title: String,
    onClick: () -> Unit
) {
    ExtendedFloatingActionButton(
        onClick = onClick,
        expanded = listState?.isScrollingUp() == true,
        icon = { Icon(imageVector = icon, contentDescription = "FAB Icon") },
        text = { Text(title) }
    )
}