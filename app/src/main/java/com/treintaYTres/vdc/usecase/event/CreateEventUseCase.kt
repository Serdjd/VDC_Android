package com.treintaYTres.vdc.usecase.event

import com.treintaYTres.vdc.network.model.request.CreateEventRequest
import com.treintaYTres.vdc.repository.EventRepository
import javax.inject.Inject

class CreateEventUseCase @Inject constructor(
    private val eventRepository: EventRepository
) {
    suspend fun execute(request: CreateEventRequest) = eventRepository.createEvent(request)
}