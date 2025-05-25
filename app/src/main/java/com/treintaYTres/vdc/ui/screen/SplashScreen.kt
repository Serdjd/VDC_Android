package com.treintaYTres.vdc.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.treintaYTres.vdc.R
import com.treintaYTres.vdc.ui.theme.VdcTheme
import com.treintaYTres.vdc.viewmodel.SignInViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    signInViewModel: SignInViewModel,
    navigate: (Boolean) -> Unit
) {

    LaunchedEffect(true) {
        coroutineScope {
            val isSignedDeferred = async {
                signInViewModel.isSigned()
            }
            val delayDeferred = async {
                delay(1000)
            }

            awaitAll(isSignedDeferred, delayDeferred)
            navigate(isSignedDeferred.await())
        }
    }

    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier.size(300.dp),
            shape = CircleShape,
            color = Color.White,
            border = BorderStroke(width = 2.dp, color = Color.LightGray)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.logo_virgen_castillo),
                    contentDescription = "Logo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(200.dp)
                )
                Text(
                    text = "VDC",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.LightGray
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SplashScreenPrev() {
    VdcTheme {
        SplashScreen(hiltViewModel()) {

        }
    }
}

