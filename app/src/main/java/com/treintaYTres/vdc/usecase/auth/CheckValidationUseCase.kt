package com.treintaYTres.vdc.usecase.auth

import com.treintaYTres.vdc.repository.AuthRepository
import com.treintaYTres.vdc.repository.UserRepository
import javax.inject.Inject

class CheckValidationUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun execute() = authRepository.checkValidation()
}