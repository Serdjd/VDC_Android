package com.treintaYTres.vdc.repository

import com.treintaYTres.vdc.network.model.request.RollCallRequest
import com.treintaYTres.vdc.network.model.response.rollcall.RollCallResponse

interface RollCallRepository {
    suspend fun getRollCall(id: Int): RollCallResponse?

    suspend fun updateRollCall(id: Int, request: List<RollCallRequest>): String?
}