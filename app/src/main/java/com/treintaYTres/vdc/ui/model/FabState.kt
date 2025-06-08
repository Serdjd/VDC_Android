package com.treintaYTres.vdc.ui.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class FabState(
    var fab: MutableState<(@Composable (() -> Unit))?> = mutableStateOf(null)
)