package com.treintaYTres.vdc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import com.treintaYTres.vdc.navigation.RootNavigation
import com.treintaYTres.vdc.ui.screen.MainScreen
import com.treintaYTres.vdc.ui.theme.VdcTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VdcTheme {
                RootNavigation(
                    Modifier,
                    rememberNavController()
                )
            }
        }
    }
}