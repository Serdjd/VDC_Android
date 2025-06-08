package com.treintaYTres.vdc.ui.model.profile

import com.treintaYTres.vdc.ui.model.people.Instrument

data class Profile(
    val name: String,
    var instruments: List<Instrument>,
    val stats: List<Stat>,
    val url: String
)
