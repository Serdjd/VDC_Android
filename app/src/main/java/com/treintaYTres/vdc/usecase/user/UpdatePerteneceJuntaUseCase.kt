package com.treintaYTres.vdc.usecase.user

import com.treintaYTres.vdc.repository.UserRepository
import javax.inject.Inject

class UpdatePerteneceJuntaUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute() = userRepository.updatePerteneceJunta()
}