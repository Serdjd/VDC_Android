package com.treintaYTres.vdc.usecase.rollcall

import com.treintaYTres.vdc.repository.RollCallRepository
import com.treintaYTres.vdc.ui.mapper.toRollCall
import javax.inject.Inject

class GetRollCallDataUseCase @Inject constructor(
    private val rollCallRepository: RollCallRepository
) {
    suspend fun execute(id: Int) = rollCallRepository.getRollCall(id)?.toRollCall()
}