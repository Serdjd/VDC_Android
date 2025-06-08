package com.treintaYTres.vdc.ui.model

data class Chip(
    val title: String,
    val icon: Int? = null,
    var onClick: () -> Unit
)

data class InstrumentChip(
    val title: String,
    val icon: String,
    val onClick: () -> Unit
)