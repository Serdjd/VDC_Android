package com.treintaYTres.vdc.viewmodel.eventdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treintaYTres.vdc.network.Result
import com.treintaYTres.vdc.ui.mapper.toEventDetails
import com.treintaYTres.vdc.ui.model.event.EventDetails
import com.treintaYTres.vdc.usecase.event.GetEventDetailsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = EventDetailsFactory::class)
class EventDetailsViewModel @AssistedInject constructor(
    @Assisted private val id: Int,
    private val getEventDetailsUseCase: GetEventDetailsUseCase
): ViewModel() {
    private val _data: MutableStateFlow<Result<EventDetails>> =
        MutableStateFlow(Result.Loading<EventDetails>())
    val data: StateFlow<Result<EventDetails>> get() = _data

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            val result = getEventDetailsUseCase.execute(id)?.let {
                Result.Success(it.toEventDetails())
            } ?: Result.Error("The data could not be loaded.")
            _data.emit(result)
        }
    }
}