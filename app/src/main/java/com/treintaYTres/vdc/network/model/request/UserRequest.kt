package com.treintaYTres.vdc.network.model.request

data class EmailUserRequest(val email: String)

data class PermissionsUserRequest(val permissions: Int)

data class InstrumentsUserRequest(
    val primaryInstrumentId: Int,
    val instrumentIds: List<Int>
)