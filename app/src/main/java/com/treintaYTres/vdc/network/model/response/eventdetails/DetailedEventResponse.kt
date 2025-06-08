package com.treintaYTres.vdc.network.model.response.eventdetails

import com.google.gson.annotations.SerializedName

data class DetailedEventResponse(
    @SerializedName("comments")
    val comments: String,
    @SerializedName("confirmed")
    val confirmed: Int,
    @SerializedName("date")
    val date: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("location")
    val location: String,
    @SerializedName("rollCallRealized")
    val rollCallRealized: Boolean,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: Int
)