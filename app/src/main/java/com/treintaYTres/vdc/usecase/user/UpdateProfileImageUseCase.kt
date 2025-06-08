package com.treintaYTres.vdc.usecase.user

import com.treintaYTres.vdc.repository.UserRepository
import java.io.File
import javax.inject.Inject

class UpdateProfileImageUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(image: File) = userRepository.updateProfileImage(image)
}