package com.treintaYTres.vdc.usecase.instrument

import com.treintaYTres.vdc.network.ApiService
import com.treintaYTres.vdc.network.model.response.instrument.InstrumentsResponse
import com.treintaYTres.vdc.repository.InstrumentRepository
import javax.inject.Inject

class InstrumentRepositoryImpl @Inject constructor(
    private val api: ApiService
): InstrumentRepository {

    override suspend fun getInstruments(): InstrumentsResponse? {
        return api.getInstruments().body()
    }

}