package com.treintaYTres.vdc.ui.model

import androidx.compose.runtime.Composable

data class TopBarState(
    var topBar: (@Composable (() -> Unit))? = null
)