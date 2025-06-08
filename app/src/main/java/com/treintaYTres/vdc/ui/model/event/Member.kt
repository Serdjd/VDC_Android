package com.treintaYTres.vdc.ui.model.event

import androidx.compose.runtime.MutableState
import com.treintaYTres.vdc.ui.model.people.Instrument

data class Member(
    val attendance: Int,
    val id: Int,
    var instruments: MutableState<List<Instrument>>,
    val isAdmin: MutableState<Boolean>,
    val name: String,
    val perteneceJunta: Boolean,
    val url: String
)
