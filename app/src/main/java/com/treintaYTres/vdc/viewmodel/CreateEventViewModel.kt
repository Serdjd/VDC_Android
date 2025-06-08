package com.treintaYTres.vdc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treintaYTres.vdc.network.Result
import com.treintaYTres.vdc.ui.mapper.toCreateEventRequest
import com.treintaYTres.vdc.ui.model.create.EventToCreate
import com.treintaYTres.vdc.ui.model.create.Group
import com.treintaYTres.vdc.usecase.event.CreateEventUseCase
import com.treintaYTres.vdc.usecase.groups.GetInstrumentStringsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateEventViewModel @Inject constructor(
    private val createEventUseCase: CreateEventUseCase,
    private val getInstrumentStringsUseCase: GetInstrumentStringsUseCase
): ViewModel() {
    private val _result: MutableSharedFlow<Result<String>> = MutableSharedFlow()
    val result: SharedFlow<Result<String>> get() = _result

    private val _groups: MutableStateFlow<Result<List<Group>>> = MutableStateFlow(Result.Loading())
    val groups: StateFlow<Result<List<Group>>> get() = _groups

    init {
        getGroups()
    }

    fun saveEvent(event: EventToCreate) {
        viewModelScope.launch {

            _result.emit(Result.Loading())
            val result = createEventUseCase.execute(event.toCreateEventRequest())

            _result.emit(
                result?.let {
                    Result.Success(it)
                } ?: Result.Error("Error al crear el evento")
            )
        }
    }

    private fun getGroups() {
        viewModelScope.launch {

            val groups = getInstrumentStringsUseCase.execute()
            _groups.emit(
                groups?.let {
                    Result.Success(it)
                } ?: Result.Error("Error del servidor")
            )

        }
    }
}