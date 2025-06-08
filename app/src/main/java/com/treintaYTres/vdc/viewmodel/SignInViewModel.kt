package com.treintaYTres.vdc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treintaYTres.vdc.network.Result
import com.treintaYTres.vdc.ui.model.auth.Validation
import com.treintaYTres.vdc.usecase.auth.CheckRegistryCompletedUseCase
import com.treintaYTres.vdc.usecase.auth.CheckValidationUseCase
import com.treintaYTres.vdc.usecase.auth.IsSignedUseCase
import com.treintaYTres.vdc.usecase.auth.SignInUseCase
import com.treintaYTres.vdc.usecase.user.GetPermissionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val isSignedUseCase: IsSignedUseCase,
    private val signInUseCase: SignInUseCase,
    private val checkValidationUseCase: CheckValidationUseCase,
    private val checkRegistryCompletedUseCase: CheckRegistryCompletedUseCase,
    private val getPermissionsUseCase: GetPermissionsUseCase
) : ViewModel() {

    private val _signInResult = MutableSharedFlow<Result<String>>()
    val signInResult: SharedFlow<Result<String>> get() = _signInResult

    private val _validation = MutableStateFlow(
        Validation(false,false,false)
    )
    val validation: StateFlow<Validation> get() = _validation

    suspend fun isSigned(): Validation {
        val result = Validation(
            isSignedUseCase.execute(),
            false,
            false
        )
        if (result.isLogged) {
            result.validate = checkValidationUseCase.execute()
            if (result.validate) {
                result.registryCompleted = checkRegistryCompletedUseCase.execute()
                if (result.registryCompleted) getPermissionsUseCase.execute()
            }
        }
        return result
    }
    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            val res = signInUseCase.execute(email, password)
            _validation.emit(isSigned())
            _signInResult.emit(res)
        }
    }
}