package com.treintaYTres.vdc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treintaYTres.vdc.network.Result
import com.treintaYTres.vdc.ui.mapper.toInstruments
import com.treintaYTres.vdc.ui.model.people.Instrument
import com.treintaYTres.vdc.usecase.instrument.GetInstrumentsUseCase
import com.treintaYTres.vdc.usecase.user.UpdateInstrumentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InstrumentsViewModel @Inject constructor(
    private val getInstrumentsUseCase: GetInstrumentsUseCase
): ViewModel() {

    private val _instruments: MutableStateFlow<Result<List<Instrument>>> =
        MutableStateFlow(Result.Loading())
    val instruments: StateFlow<Result<List<Instrument>>> get() = _instruments

    init {
        viewModelScope.launch {
            val result = getInstrumentsUseCase.execute()
            if (result != null) {
                _instruments.emit(
                    Result.Success(result.toInstruments())
                )
            }
            else _instruments.emit(Result.Error("No se pudieron obtener los datos"))
        }
    }
}