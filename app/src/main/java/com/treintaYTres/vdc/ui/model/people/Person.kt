package com.treintaYTres.vdc.ui.model.people

data class Person(
    val id: Int,
    val url: String,
    val name: String,
    val assistance: Int = 2,
    val isAdministrator: Boolean = false,
    val instrument: Instrument? = null
)
