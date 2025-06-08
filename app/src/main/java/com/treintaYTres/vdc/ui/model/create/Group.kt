package com.treintaYTres.vdc.ui.model.create

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Group(
    val id: Int,
    val name: String,
    val url: String,
    val selected: MutableState<Boolean> = mutableStateOf(false)
)
