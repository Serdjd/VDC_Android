package com.treintaYTres.vdc.ui.model.rollcall

import androidx.compose.runtime.MutableState
import com.treintaYTres.vdc.ui.model.people.Instrument

data class RollCallItem(
    val attendance: MutableState<Boolean>,
    val id: Int,
    val instrument: Instrument,
    val name: String,
    val url: String
)
