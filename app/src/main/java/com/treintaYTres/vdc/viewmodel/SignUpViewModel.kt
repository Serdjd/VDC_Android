package com.treintaYTres.vdc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treintaYTres.vdc.network.Result
import com.treintaYTres.vdc.usecase.auth.SignInUseCase
import com.treintaYTres.vdc.usecase.auth.SignUpUseCase
import com.treintaYTres.vdc.usecase.user.CreateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val createUserUseCase: CreateUserUseCase
): ViewModel(){
    private val _signUpResult = MutableSharedFlow<Result<String>>()
    val signUpResult: SharedFlow<Result<String>> get() = _signUpResult

    private val _userCreated = MutableSharedFlow<Boolean>()
    val userCreated: SharedFlow<Boolean> get() = _userCreated

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            _signUpResult.emit(signUpUseCase.execute(email, password))
        }
    }

    fun createUser(email: String) {
        viewModelScope.launch {
            _userCreated.emit(createUserUseCase.execute(email) != -1)
        }
    }
}