package com.treintaYTres.vdc.ui.screen.authscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.treintaYTres.vdc.R
import com.treintaYTres.vdc.ui.component.CurvedText
import com.treintaYTres.vdc.ui.theme.VdcTheme

@Composable
fun WaitingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            32.dp,
            Alignment.CenterVertically
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CurvedText(
                Modifier
                    .zIndex(1f)
                    .offset(0.dp, 50.dp),
                "Esperando revisión",
                32
            )
            Surface(
                modifier = Modifier
                    .size(350.dp),
                shape = CircleShape
            ) {
                Box(
                    modifier = Modifier
                        .padding(15.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(300.dp),
                        painter = painterResource(R.drawable.hourglass),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Text(
                text = "Un administrador revisará tu información.",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Te notificaremos cuando puedas continuar.",
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun WaitingScreenPrev() {
    VdcTheme {
        WaitingScreen()
    }
}