package com.treintaYTres.vdc.usecase.event

import android.content.SharedPreferences
import com.treintaYTres.vdc.network.ApiService
import com.treintaYTres.vdc.network.model.request.AttendanceRequest
import com.treintaYTres.vdc.network.model.request.CreateEventRequest
import com.treintaYTres.vdc.network.model.response.event.EventResponse
import com.treintaYTres.vdc.network.model.response.eventdetails.EventDetailsResponse
import com.treintaYTres.vdc.network.model.types.EventFilter
import com.treintaYTres.vdc.repository.EventRepository
import com.treintaYTres.vdc.util.getUserId
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val sharedPreferences: SharedPreferences
) : EventRepository {
    override suspend fun getEvents(filter: EventFilter): EventResponse? {
        val id = sharedPreferences.getUserId()
        return api.getEvents(id, filter.name.lowercase()).body()
    }

    override suspend fun createEvent(request: CreateEventRequest): String? {
        return api.createEvent(request).body()
    }

    override suspend fun modifyEvent(request: CreateEventRequest): String? {
        return api.modifyEvent(request).body()
    }

    override suspend fun getEventDetails(id: Int): EventDetailsResponse? {
        return api.getEventDetails(id).body()
    }

    override suspend fun updateAttendance(
        eventId: Int,
        attendance: Boolean
    ): String? {
        return api.updateAttendance(
            eventId,
            sharedPreferences.getUserId(),
            AttendanceRequest(attendance)
        ).body()
    }
}