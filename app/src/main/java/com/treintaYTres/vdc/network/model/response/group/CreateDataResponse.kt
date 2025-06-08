package com.treintaYTres.vdc.network.model.response.group

import com.google.gson.annotations.SerializedName

data class CreateDataResponse(
    @SerializedName("groups")
    val groups: List<Group>
)