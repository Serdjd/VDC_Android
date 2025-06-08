package com.treintaYTres.vdc.network.model.response.profile


import com.google.gson.annotations.SerializedName

data class StatResponse(
    @SerializedName("assistance")
    val assistance: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("type")
    val type: Int
)