package com.treintaYTres.vdc.usecase.auth

import com.treintaYTres.vdc.repository.AuthRepository
import javax.inject.Inject

class DeleteMemberUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun execute(id: Int) = authRepository.deleteMember(id)
}