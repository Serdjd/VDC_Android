package com.treintaYTres.vdc.repository

import com.treintaYTres.vdc.network.model.request.CreateEventRequest
import com.treintaYTres.vdc.network.model.response.event.EventResponse
import com.treintaYTres.vdc.network.model.response.eventdetails.EventDetailsResponse
import com.treintaYTres.vdc.network.model.types.EventFilter

interface EventRepository {
    suspend fun getEvents(
        filter: EventFilter
    ): EventResponse?

    suspend fun createEvent(
        request: CreateEventRequest
    ): String?

    suspend fun modifyEvent(
        request: CreateEventRequest
    ): String?

    suspend fun getEventDetails(id: Int): EventDetailsResponse?

    suspend fun updateAttendance(
        eventId: Int,
        attendance: Boolean
    ): String?
}