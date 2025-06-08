package com.treintaYTres.vdc.usecase.band

import com.treintaYTres.vdc.repository.BandRepository
import javax.inject.Inject

class GetBandInfoUseCase @Inject constructor(
    private val bandRepository: BandRepository
) {
    suspend fun execute() = bandRepository.getBandInfo();
}