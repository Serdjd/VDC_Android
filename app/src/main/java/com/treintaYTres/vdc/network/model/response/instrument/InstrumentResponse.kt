package com.treintaYTres.vdc.network.model.response.instrument

import com.google.gson.annotations.SerializedName

data class InstrumentResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)