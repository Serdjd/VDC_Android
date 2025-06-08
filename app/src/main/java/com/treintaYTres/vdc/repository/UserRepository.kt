package com.treintaYTres.vdc.repository

import com.treintaYTres.vdc.network.model.request.CompleteRegistryRequest
import com.treintaYTres.vdc.network.model.response.member.MemberResponse
import java.io.File

interface UserRepository {
    suspend fun getUserData(id: Int): MemberResponse?

    suspend fun createUser(email: String): Int

    suspend fun completeRegistry(
        image: File,
        data: CompleteRegistryRequest
    ): String?

    suspend fun updateProfileImage(
        image: File
    ): String?

    suspend fun updatePermissions(id: Int): String?

    suspend fun updateInstruments(
        id: Int,
        instrumentIds: List<Int>
    ): String?

    suspend fun updateInstruments(
        instrumentIds: List<Int>
    ): String?

    suspend fun updatePerteneceJunta(): String?

    suspend fun getPermissions()
}