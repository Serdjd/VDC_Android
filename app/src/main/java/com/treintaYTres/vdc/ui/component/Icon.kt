package com.treintaYTres.vdc.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import com.treintaYTres.vdc.ui.theme.VdcTheme

@Composable
fun InstrumentIcon(
    model: String,
    isPrimary: Boolean
) {
    Box(
        modifier = Modifier.size(48.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Surface(
            shape = CircleShape,
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.outline),
            color = Color.Transparent
        ) {
            IconButton(onClick = {}) {
                Icon(
                    painter = rememberAsyncImagePainter(
                        model = model,
                        contentScale = ContentScale.Crop
                    ),
                    contentDescription = "Instrument"
                )
            }
        }
        if (isPrimary) {
            Icon(
                modifier = Modifier.offset(1.75.dp, 3.dp),
                imageVector = Icons.Sharp.Star,
                tint = MaterialTheme.colorScheme.inversePrimary,
                contentDescription = "Is primary Instrument"
            )
        }
    }
}

@Preview
@Composable
fun InstrumentIconPrev() {
    VdcTheme {
        InstrumentIcon(
            "",
            true
        )
    }
}