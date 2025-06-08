package com.treintaYTres.vdc.usecase.rollcall

import com.treintaYTres.vdc.network.ApiService
import com.treintaYTres.vdc.network.model.request.RollCallRequest
import com.treintaYTres.vdc.network.model.response.rollcall.RollCallResponse
import com.treintaYTres.vdc.repository.RollCallRepository
import javax.inject.Inject

class RollCallRepositoryImpl @Inject constructor(
    private val api: ApiService
): RollCallRepository {
    override suspend fun getRollCall(id: Int): RollCallResponse? {
        return api.getRollCall(id).body()
    }

    override suspend fun updateRollCall(
        id: Int,
        request: List<RollCallRequest>
    ): String? {
        return api.updateRollCall(id,request).body()
    }
}