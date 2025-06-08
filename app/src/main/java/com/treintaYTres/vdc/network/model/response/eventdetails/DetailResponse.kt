package com.treintaYTres.vdc.network.model.response.eventdetails

import com.google.gson.annotations.SerializedName
import com.treintaYTres.vdc.network.model.response.member.MemberResponse

data class DetailResponse(
    @SerializedName("instrumentString")
    val instrumentString: InstrumentStringResponse,
    @SerializedName("members")
    val members: List<MemberResponse>
)