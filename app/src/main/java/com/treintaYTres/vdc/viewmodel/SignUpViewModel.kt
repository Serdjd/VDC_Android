package com.treintaYTres.vdc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treintaYTres.vdc.network.Result
import com.treintaYTres.vdc.usecase.auth.SignInUseCase
import com.treintaYTres.vdc.usecase.auth.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
): ViewModel(){
    private val _signUpResult = MutableSharedFlow<com.treintaYTres.vdc.network.Result<String>>()
    val signUpResult: SharedFlow<Result<String>> = _signUpResult

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            _signUpResult.emit(signUpUseCase.execute(email, password))
        }
    }
}