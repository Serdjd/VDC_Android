package com.treintaYTres.vdc.usecase.auth

import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.treintaYTres.vdc.network.ApiService
import com.treintaYTres.vdc.network.Result
import com.treintaYTres.vdc.repository.AuthRepository
import com.treintaYTres.vdc.util.getUserId
import com.treintaYTres.vdc.util.saveUserId
import com.treintaYTres.vdc.util.updateProfileUrl
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val api: ApiService,
    private val sharedPreferences: SharedPreferences
): AuthRepository {

    override fun isSigned(): Boolean {
        val user = auth.currentUser
        return user != null
    }

    override suspend fun signIn(email: String, password: String): Result<String> {
        return try {
            val result = auth.signInWithEmailAndPassword(email,password).await()
            val email = result.user?.email

            if (email == null) Result.Error<String>("Error")
            else {
                val res = api.getUserId(email).body()

                if (res != null) {
                    sharedPreferences.saveUserId(res)
                    sharedPreferences.updateProfileUrl("${email}.png")
                    Result.Success("Succesfull")
                } else {
                    Result.Error<String>("Error")
                }
            }

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

    override suspend fun checkValidation(): Boolean {
        val id = sharedPreferences.getUserId()
        return api.checkValidation(id).body() == true
    }

    override suspend fun checkRegistryCompleted(): Boolean {
        return api.checkRegistryCompleted(sharedPreferences.getUserId()).body() == true
    }

    override suspend fun deleteMember(id: Int): Boolean {
        return api.deleteMember(id).isSuccessful
    }
}