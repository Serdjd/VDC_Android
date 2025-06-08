package com.treintaYTres.vdc.network.model.request

data class CompleteRegistryRequest(
    val instrumentIds: List<Int>,
    val primaryInstrumentId: Int,
    val username: String
)