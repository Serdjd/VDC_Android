package com.treintaYTres.vdc.repository

import com.treintaYTres.vdc.network.Result

interface AuthRepository {
    fun isSigned(): Boolean
    suspend fun signIn(email: String, password: String): Result<String>
    suspend fun signUp(email: String, password: String): Result<String>
    suspend fun checkValidation(): Boolean
    suspend fun checkRegistryCompleted(): Boolean
    suspend fun deleteMember(id: Int): Boolean
}