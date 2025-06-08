package com.treintaYTres.vdc.usecase.user

import com.treintaYTres.vdc.network.model.request.CompleteRegistryRequest
import com.treintaYTres.vdc.repository.UserRepository
import java.io.File
import javax.inject.Inject

class CompleteRegistryUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(image: File, data: CompleteRegistryRequest) = userRepository.completeRegistry(image,data)
}