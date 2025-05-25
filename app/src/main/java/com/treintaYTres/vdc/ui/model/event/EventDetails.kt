package com.treintaYTres.vdc.ui.model.event

data class EventDetails(
    val basicInfo: BasicInfo,
    val comments: Comment,
    val adminInfo: BasicInfo?
)