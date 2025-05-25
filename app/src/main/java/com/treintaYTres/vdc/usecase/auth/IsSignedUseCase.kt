package com.treintaYTres.vdc.usecase.auth

import com.treintaYTres.vdc.repository.AuthRepository
import javax.inject.Inject

class IsSignedUseCase @Inject constructor(private val authRepository: AuthRepository) {
    fun execute() = authRepository.isSigned()
}