package com.treintaYTres.vdc.network.model.response.instrument

import com.google.gson.annotations.SerializedName

data class InstrumentsResponse(
    @SerializedName("instruments")
    val instruments: List<InstrumentResponse>
)
