package com.treintaYTres.vdc.ui.model.event

import androidx.compose.runtime.MutableState

data class Event(
    val confirmationState: MutableState<Int>,
    var date: String,
    val id: Int,
    val location: String,
    val title: String,
    val type: Int
)

data class DetailedEvent(
    val comments: String,
    val confirmed: Int,
    val date: String,
    val id: Int,
    val location: String,
    val rollCallRealized: Boolean,
    val title: String,
    val type: Int
)