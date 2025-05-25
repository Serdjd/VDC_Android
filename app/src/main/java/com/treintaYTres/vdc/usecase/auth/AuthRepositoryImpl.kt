package com.treintaYTres.vdc.usecase.auth

import com.google.firebase.auth.FirebaseAuth
import com.treintaYTres.vdc.network.Result
import com.treintaYTres.vdc.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val auth: FirebaseAuth): AuthRepository {

    override fun isSigned(): Boolean {
        val user = auth.currentUser
        return user != null
    }

    override suspend fun signIn(email: String, password: String): Result<String> {
        return try {
            val result = auth.signInWithEmailAndPassword(email,password).await()
            Result.Success(result.user?.email ?: "")
        } catch (e: Exception) {
            Result.Error<String>(e.message ?: "")
        }
    }

    override suspend fun signUp(email: String, password: String): Result<String> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email,password).await()
            Result.Success(result.user?.email ?: "")
        } catch (e: Exception) {
            Result.Error<String>(e.message ?: "")
        }
    }
}