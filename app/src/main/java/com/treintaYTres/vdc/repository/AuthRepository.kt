package com.treintaYTres.vdc.repository

import com.treintaYTres.vdc.network.Result

interface AuthRepository {
    fun isSigned(): Boolean
    suspend fun signIn(email: String, password: String): Result<String>
    suspend fun signUp(email: String, password: String): Result<String>
}