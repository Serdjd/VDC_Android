package com.treintaYTres.vdc.ui.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf

val localUiController = staticCompositionLocalOf<UiController> {
    error("No UiController provided")
}

class UiController {
    var topBarState by mutableStateOf(TopBarState())

    var fabState by mutableStateOf(FabState())

    fun clearTopBar() {
        topBarState = TopBarState()
    }

    fun clearFab() {
        fabState = FabState()
    }

}