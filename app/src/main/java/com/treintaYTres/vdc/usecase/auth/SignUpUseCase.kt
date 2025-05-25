package com.treintaYTres.vdc.usecase.auth

import com.treintaYTres.vdc.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend fun execute(
        email: String,
        password: String
    ) = authRepository.signUp(email,password)
}