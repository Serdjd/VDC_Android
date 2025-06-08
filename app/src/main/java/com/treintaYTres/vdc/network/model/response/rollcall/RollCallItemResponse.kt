package com.treintaYTres.vdc.network.model.response.rollcall


import com.google.gson.annotations.SerializedName
import com.treintaYTres.vdc.network.model.response.instrument.InstrumentResponse

data class RollCallItemResponse(
    @SerializedName("attendance")
    val attendance: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("instruments")
    val instruments: List<InstrumentResponse>,
    @SerializedName("isAdmin")
    val isAdmin: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("perteneceJunta")
    val perteneceJunta: Boolean,
    @SerializedName("url")
    val url: String
)