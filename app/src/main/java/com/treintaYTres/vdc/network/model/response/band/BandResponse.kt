package com.treintaYTres.vdc.network.model.response.band

import com.google.gson.annotations.SerializedName
import com.treintaYTres.vdc.network.model.response.member.MemberResponse

data class BandResponse(
    @SerializedName("admin")
    val isAdmin: Boolean,
    @SerializedName("members")
    val members: List<MemberResponse>,
    @SerializedName("news")
    val news: List<NewResponse>
)