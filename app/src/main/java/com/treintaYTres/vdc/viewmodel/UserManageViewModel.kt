package com.treintaYTres.vdc.viewmodel

import androidx.lifecycle.ViewModel
import com.treintaYTres.vdc.usecase.user.UpdateInstrumentsUseCase
import com.treintaYTres.vdc.usecase.user.UpdatePermissionsUseCase
import com.treintaYTres.vdc.usecase.user.UpdatePerteneceJuntaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserManageViewModel @Inject constructor(
    private val updatePermissionsUseCase: UpdatePermissionsUseCase,
    private val updatePerteneceJuntaUseCase: UpdatePerteneceJuntaUseCase,
    private val updateInstrumentsUseCase: UpdateInstrumentsUseCase
): ViewModel() {
}