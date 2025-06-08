package com.treintaYTres.vdc.network.model.response.eventdetails

import com.google.gson.annotations.SerializedName

data class InstrumentStringResponse(
    @SerializedName("cancelled")
    val cancelled: Int,
    @SerializedName("confirmed")
    val confirmed: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String?
)