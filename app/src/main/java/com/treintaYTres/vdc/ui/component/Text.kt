package com.treintaYTres.vdc.ui.component

import android.graphics.Path
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.treintaYTres.vdc.ui.theme.VdcTheme

@Composable
fun TextHeader(
    text: String
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
fun TextBody(
    text: String,
    maxLines: Int = 5
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun TextTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.displayMedium
    )
}

@Composable
fun TextTitleMedium(
    text: String
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium
    )
}


@Composable
fun CurvedText(
    modifier: Modifier,
    text: String,
    size: Int = 16
) {
    Canvas(
        modifier = modifier.size(300.dp,110.dp)
    ) {
        drawIntoCanvas {
            val textPadding = 48.dp.toPx()
            val arcHeight = 280.dp.toPx()
            val arcWidth = 300.dp.toPx()
            val path = Path().apply {
                addArc(0f, textPadding, arcWidth, arcHeight, 180f, 180f)
            }
            it.nativeCanvas.drawTextOnPath(
                text,
                path,
                0f,
                0f,
                Paint().apply {
                    textSize = size.sp.toPx()
                    textAlign = Paint.Align.CENTER
                }
            )
        }
    }
}

@Preview
@Composable
fun CurvedTextPrev() {
    VdcTheme {
        CurvedText(
            Modifier,
            "Esperando revisión",
            32
        )
    }
}