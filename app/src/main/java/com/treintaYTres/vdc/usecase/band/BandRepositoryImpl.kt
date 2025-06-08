package com.treintaYTres.vdc.usecase.band

import android.content.SharedPreferences
import com.treintaYTres.vdc.network.ApiService
import com.treintaYTres.vdc.network.model.response.band.BandResponse
import com.treintaYTres.vdc.repository.BandRepository
import com.treintaYTres.vdc.util.getUserId
import javax.inject.Inject

class BandRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val sharedPreferences: SharedPreferences
): BandRepository {

    override suspend fun getBandInfo(): BandResponse? {
        return api.getBandInfo(sharedPreferences.getUserId()).body()
    }
}