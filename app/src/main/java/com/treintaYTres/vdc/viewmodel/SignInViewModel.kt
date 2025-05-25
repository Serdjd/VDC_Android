package com.treintaYTres.vdc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treintaYTres.vdc.network.Result
import com.treintaYTres.vdc.usecase.auth.IsSignedUseCase
import com.treintaYTres.vdc.usecase.auth.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val isSignedUseCase: IsSignedUseCase,
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _signInResult = MutableSharedFlow<Result<String>>()
    val signInResult: SharedFlow<Result<String>> = _signInResult

    fun isSigned() = isSignedUseCase.execute()
    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _signInResult.emit(signInUseCase.execute(email, password))
        }
    }
}