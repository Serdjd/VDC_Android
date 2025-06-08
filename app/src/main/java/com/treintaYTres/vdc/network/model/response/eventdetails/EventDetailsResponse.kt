package com.treintaYTres.vdc.network.model.response.eventdetails

import com.google.gson.annotations.SerializedName

data class EventDetailsResponse(
    @SerializedName("details")
    val details: List<DetailResponse>,
    @SerializedName("event")
    val event: DetailedEventResponse
)