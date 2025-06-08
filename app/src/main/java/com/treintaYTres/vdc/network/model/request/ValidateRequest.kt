package com.treintaYTres.vdc.network.model.request


import com.google.gson.annotations.SerializedName

data class ValidateRequest(
    @SerializedName("validate")
    val validate: Boolean
)