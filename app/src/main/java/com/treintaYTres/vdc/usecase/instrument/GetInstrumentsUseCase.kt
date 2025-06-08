package com.treintaYTres.vdc.usecase.instrument

import com.treintaYTres.vdc.repository.InstrumentRepository
import javax.inject.Inject

class GetInstrumentsUseCase @Inject constructor(
    private val instrumentRepository: InstrumentRepository
) {
    suspend fun execute() = instrumentRepository.getInstruments()
}