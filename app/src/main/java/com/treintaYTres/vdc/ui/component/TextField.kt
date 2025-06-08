package com.treintaYTres.vdc.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun OutlinedTextField(
    modifier: Modifier,
    state: String,
    placeholder: String,
    icon: ImageVector? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    readOnly: Boolean = false,
    onValueChange: (String) -> Unit
) {
    if (icon != null) {
        OutlinedTextField(
            modifier = modifier.padding(),
            value = state,
            label = { Text(placeholder) },
            onValueChange = { onValueChange.invoke(it) },
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "Trailing Icon"
                )
            },
            singleLine = singleLine,
            enabled = enabled,
            readOnly = readOnly
        )
    } else {
        OutlinedTextField(
            modifier = modifier.padding(),
            value = state,
            label = { Text(placeholder) },
            onValueChange = { onValueChange.invoke(it) },
            singleLine = singleLine,
            enabled = enabled,
            readOnly = readOnly
        )
    }

}

@Composable
fun OutlinedTextField(
    modifier: Modifier,
    state: State<String>,
    placeholder: String,
    icon: Painter,
    readOnly: Boolean = false,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier.padding(),
        value = state.value,
        label = { Text(placeholder) },
        onValueChange = { onValueChange.invoke(it) },
        leadingIcon = {
            Icon(
                painter = icon,
                contentDescription = "Trailing Icon"
            )
        },
        singleLine = true,
        readOnly = readOnly
    )
}