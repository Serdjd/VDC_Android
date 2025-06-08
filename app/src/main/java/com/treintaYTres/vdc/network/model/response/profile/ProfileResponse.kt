package com.treintaYTres.vdc.network.model.response.profile


import com.google.gson.annotations.SerializedName
import com.treintaYTres.vdc.network.model.response.instrument.InstrumentResponse

data class ProfileResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("instruments")
    val instruments: List<InstrumentResponse>,
    @SerializedName("stats")
    val stats: List<StatResponse>,
    @SerializedName("url")
    val url: String
)