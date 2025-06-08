package com.treintaYTres.vdc.usecase.user

import com.treintaYTres.vdc.repository.UserRepository
import javax.inject.Inject

class UpdatePermissionsUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(id: Int) = userRepository.updatePermissions(id)
}