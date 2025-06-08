package com.treintaYTres.vdc.repository

import com.treintaYTres.vdc.network.model.response.band.BandResponse

interface BandRepository {
    suspend fun getBandInfo(): BandResponse?
}