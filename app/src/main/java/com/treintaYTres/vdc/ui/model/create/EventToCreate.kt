package com.treintaYTres.vdc.ui.model.create

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf

data class EventToCreate(
    val type: MutableState<Int> = mutableIntStateOf(0),
    val title: MutableState<String> = mutableStateOf(""),
    val comments: MutableState<String> = mutableStateOf(""),
    val location: MutableState<String> = mutableStateOf(""),
    val date: MutableState<String> = mutableStateOf(""),
    val time: MutableState<String> = mutableStateOf(""),
    val instrumentStringIds: MutableList<Int> = mutableListOf<Int>(),
)