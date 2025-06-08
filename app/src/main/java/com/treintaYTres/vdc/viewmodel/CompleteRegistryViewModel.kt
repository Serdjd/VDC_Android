package com.treintaYTres.vdc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treintaYTres.vdc.network.model.request.CompleteRegistryRequest
import com.treintaYTres.vdc.usecase.user.CompleteRegistryUseCase
import com.treintaYTres.vdc.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CompleteRegistryViewModel @Inject constructor(
    private val completeRegistryUseCase: CompleteRegistryUseCase
): ViewModel() {

    private val _registryResult = MutableSharedFlow<Result<Boolean>>()
    val registryResult: SharedFlow<Result<Boolean>> get() = _registryResult

    fun completeRegistry(image: File, data: CompleteRegistryRequest) {
        viewModelScope.launch {
            _registryResult.emit(
                if (completeRegistryUseCase.execute(image, data) == null) {
                    Result.Error("Intentalo más tarde")
                } else {
                    Result.Success(true)
                }
            )
        }
    }
}