package com.treintaYTres.vdc.usecase.rollcall

import com.treintaYTres.vdc.network.model.request.RollCallRequest
import com.treintaYTres.vdc.network.model.response.rollcall.RollCallResponse
import com.treintaYTres.vdc.repository.RollCallRepository
import com.treintaYTres.vdc.ui.mapper.toRollCall
import javax.inject.Inject

class UpdateRollCallDataUseCase @Inject constructor(
    private val rollCallRepository: RollCallRepository
) {
    suspend fun execute(id: Int, request: List<RollCallRequest>) = rollCallRepository.updateRollCall(id,request)
}