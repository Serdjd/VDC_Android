package com.treintaYTres.vdc.network.model.request


import com.google.gson.annotations.SerializedName

data class CreateEventRequest(
    @SerializedName("type")
    val type: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("comments")
    val comments: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("instrumentStringIds")
    val instrumentStringIds: List<Int>
)