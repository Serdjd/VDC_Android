package com.treintaYTres.vdc.usecase.groups

import com.treintaYTres.vdc.repository.CreateDataRepository
import com.treintaYTres.vdc.ui.mapper.toGroups
import javax.inject.Inject

class GetInstrumentStringsUseCase @Inject constructor(
    private val createDataRepository: CreateDataRepository
) {
    suspend fun execute() = createDataRepository.getCreateData()?.groups?.toGroups()
}