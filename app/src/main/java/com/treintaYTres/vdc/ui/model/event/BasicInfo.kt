package com.treintaYTres.vdc.ui.model.event

import com.treintaYTres.vdc.ui.model.Header
import com.treintaYTres.vdc.ui.model.RowInfo

data class BasicInfo(
    val header: Header,
    val info: List<RowInfo>
)
