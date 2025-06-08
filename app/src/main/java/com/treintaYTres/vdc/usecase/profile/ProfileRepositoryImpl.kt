package com.treintaYTres.vdc.usecase.profile

import android.content.SharedPreferences
import com.treintaYTres.vdc.network.ApiService
import com.treintaYTres.vdc.network.model.response.profile.ProfileResponse
import com.treintaYTres.vdc.repository.ProfileRepository
import com.treintaYTres.vdc.util.getUserId
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val sharedPreferences: SharedPreferences
): ProfileRepository {
    override suspend fun getProfile(): ProfileResponse? {
        return api.getProfile(sharedPreferences.getUserId()).body()
    }

}