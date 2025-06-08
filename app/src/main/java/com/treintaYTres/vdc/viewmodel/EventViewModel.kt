package com.treintaYTres.vdc.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treintaYTres.vdc.ui.mapper.toEvents
import com.treintaYTres.vdc.ui.model.event.Event
import com.treintaYTres.vdc.network.Result
import com.treintaYTres.vdc.network.model.types.EventFilter
import com.treintaYTres.vdc.network.model.types.EventType
import com.treintaYTres.vdc.ui.util.toText
import com.treintaYTres.vdc.usecase.event.GetEventsUseCase
import com.treintaYTres.vdc.usecase.event.UpdateAttendanceUseCase
import com.treintaYTres.vdc.util.getUserIsAdmin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.ocpsoft.prettytime.PrettyTime
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase,
    private val updateAttendanceUseCase: UpdateAttendanceUseCase,
    private val prefs: SharedPreferences,
    private val prettyTime: PrettyTime
) : ViewModel() {

    private val _events: MutableStateFlow<Result<List<Event>>> =
        MutableStateFlow(Result.Loading<List<Event>>())

    private val _selectedType =
        MutableStateFlow<EventType>(EventType.ALL)

    private var _selectedFilter = EventFilter.FUTURE
    val selectedFilter get() = _selectedFilter

    val events: Flow<Result<List<Event>>> =
        combine(_events, _selectedType) { events, type ->
            when (events) {
                is Result.Success<*> -> {
                    if (type != EventType.ALL) {
                        events.data?.let {
                            val list = it.filter {
                                (it.type + 1) == type.ordinal
                            }
                            Result.Success(list)
                        } ?: events
                    } else events
                }

                else -> events
            }
        }

    init {
        getEvents(EventFilter.FUTURE)
    }

    fun getEvents(filter: EventFilter = _selectedFilter) {
        _selectedFilter = filter
        viewModelScope.launch {
            val result = getEventsUseCase.execute(filter)
            if (result == null) {
                _events.emit(Result.Error("Server Error"))
            } else {
                _events.emit(Result.Success(result.toEvents().map {
                    it.apply {
                        this.date = prettyTime.toText(this.date)
                    }
                }))
            }
        }
    }

    fun changeType(type: EventType) {
        viewModelScope.launch {
            _selectedType.emit(type)
        }
    }

    fun isAdmin() = prefs.getUserIsAdmin()

    fun updateAttendance(
        eventId: Int,
        attendance: Boolean
    ) {
        viewModelScope.launch {
            updateAttendanceUseCase.execute(
                eventId,
                attendance
            )
        }
    }
}