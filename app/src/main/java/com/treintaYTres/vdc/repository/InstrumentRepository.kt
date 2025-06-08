package com.treintaYTres.vdc.repository

import com.treintaYTres.vdc.network.model.response.instrument.InstrumentsResponse

interface InstrumentRepository {
    suspend fun getInstruments(): InstrumentsResponse?
}