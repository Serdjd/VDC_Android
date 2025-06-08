package com.treintaYTres.vdc.usecase.user

import com.treintaYTres.vdc.repository.UserRepository
import javax.inject.Inject

class UpdateInstrumentsUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(id: Int, instruments: List<Int>) = userRepository.updateInstruments(id,instruments)
    suspend fun execute(instruments: List<Int>) = userRepository.updateInstruments(instruments)
}