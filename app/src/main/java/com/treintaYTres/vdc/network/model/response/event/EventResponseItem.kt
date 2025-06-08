package com.treintaYTres.vdc.network.model.response.event

import com.google.gson.annotations.SerializedName

data class EventResponseItem(
    @SerializedName("confirmationState")
    val confirmationState: Boolean?,
    @SerializedName("date")
    val date: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("location")
    val location: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: Int
)