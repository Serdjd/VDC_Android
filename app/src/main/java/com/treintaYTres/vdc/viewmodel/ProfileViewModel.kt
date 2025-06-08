package com.treintaYTres.vdc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treintaYTres.vdc.network.Result
import com.treintaYTres.vdc.ui.model.people.Instrument
import com.treintaYTres.vdc.ui.model.profile.Profile
import com.treintaYTres.vdc.usecase.profile.GetProfileUseCase
import com.treintaYTres.vdc.usecase.user.UpdateInstrumentsUseCase
import com.treintaYTres.vdc.usecase.user.UpdateProfileImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val updateProfileImageUseCase: UpdateProfileImageUseCase,
    private val updateInstrumentsUseCase: UpdateInstrumentsUseCase
): ViewModel() {

    private val _profile: MutableStateFlow<Result<Profile>> = MutableStateFlow(Result.Loading())
    val profile: StateFlow<Result<Profile>> get() = _profile

    init {
        getProfile()
    }

    fun getProfile() {
        viewModelScope.launch {
            val result = getProfileUseCase.execute()
            _profile.emit(
                result?.let {
                    Result.Success(it)
                } ?: Result.Error("No se pudieron cargar los datos")
            )
        }
    }

    fun updatePhoto(image: File) {
        viewModelScope.launch {
            updateProfileImageUseCase.execute(image)
        }
    }

    fun updateInstruments(instruments: List<Instrument>) {
        viewModelScope.launch {
            updateInstrumentsUseCase.execute(instruments.map { it.id })
        }
    }
}