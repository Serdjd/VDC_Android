package com.treintaYTres.vdc.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.treintaYTres.vdc.R

@Composable
fun CheckButton(
    modifier: Modifier = Modifier,
    size: Int = 48,
    onclick: () -> Unit
) {
    IconButton(onClick = onclick) {
        Icon(
            modifier = modifier.size(size.dp),
            painter = painterResource(R.drawable.check),
            contentDescription = "Go Icon",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun CrossButton(
    modifier: Modifier = Modifier,
    size: Int = 48,
    onclick: () -> Unit
) {
    IconButton(onClick = onclick) {
        Icon(
            modifier = modifier.size(size.dp),
            painter = painterResource(R.drawable.cancel),
            contentDescription = "Don't Go Icon",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}