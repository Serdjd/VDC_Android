package com.treintaYTres.vdc.repository

import com.treintaYTres.vdc.network.model.response.profile.ProfileResponse

interface ProfileRepository {
    suspend fun getProfile(): ProfileResponse?
}