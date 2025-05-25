package com.treintaYTres.vdc.ui.model

import androidx.compose.runtime.Composable

data class FabState(
    var fab: (@Composable (() -> Unit))? = null
)