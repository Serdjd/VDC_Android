package com.treintaYTres.vdc.ui.model.band

import com.treintaYTres.vdc.ui.model.New
import com.treintaYTres.vdc.ui.model.event.Member

data class BandInfo(
    val isAdmin: Boolean,
    val members: MutableList<Member>,
    val news: List<New>
)