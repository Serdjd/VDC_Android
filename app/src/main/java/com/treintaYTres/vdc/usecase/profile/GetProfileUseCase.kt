package com.treintaYTres.vdc.usecase.profile

import com.treintaYTres.vdc.repository.ProfileRepository
import com.treintaYTres.vdc.ui.mapper.toProfile
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend fun execute() = profileRepository.getProfile()?.toProfile()
}