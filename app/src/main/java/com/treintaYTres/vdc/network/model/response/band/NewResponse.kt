package com.treintaYTres.vdc.network.model.response.band

import com.google.gson.annotations.SerializedName

data class NewResponse(
    @SerializedName("date")
    val date: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("text")
    val text: String,
    @SerializedName("title")
    val title: String
)