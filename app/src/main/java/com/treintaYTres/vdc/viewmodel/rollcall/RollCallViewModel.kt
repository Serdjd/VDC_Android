package com.treintaYTres.vdc.viewmodel.rollcall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treintaYTres.vdc.network.Result
import com.treintaYTres.vdc.network.model.request.RollCallRequest
import com.treintaYTres.vdc.network.model.response.rollcall.RollCallResponse
import com.treintaYTres.vdc.ui.model.rollcall.RollCallItem
import com.treintaYTres.vdc.usecase.rollcall.GetRollCallDataUseCase
import com.treintaYTres.vdc.usecase.rollcall.UpdateRollCallDataUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = RollCallFactory::class)
class RollCallViewModel @AssistedInject constructor(
    @Assisted private val id: Int,
    private val getRollCallDataUseCase: GetRollCallDataUseCase,
    private val updateRollCallDataUseCase: UpdateRollCallDataUseCase
) : ViewModel() {
    private val _data: MutableStateFlow<Result<List<RollCallItem>>> =
        MutableStateFlow(Result.Loading())
    val data: StateFlow<Result<List<RollCallItem>>> get() = _data

    private val _result = MutableSharedFlow<Result<String>>()
    val result: SharedFlow<Result<String>> get() = _result

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            val result = getRollCallDataUseCase.execute(id)

            result?.let {
                _data.emit(Result.Success(it))
            } ?: _data.emit(Result.Error("Fallo del server"))
        }
    }

    fun updateData(request: List<RollCallRequest>) {
        viewModelScope.launch {
            val result = updateRollCallDataUseCase.execute(id,request)

            result?.let {
                _result.emit(Result.Success(it))
            } ?: _result.emit(Result.Error("Fallo del server"))
        }
    }
}