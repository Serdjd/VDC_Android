package com.treintaYTres.vdc.usecase.event

import com.treintaYTres.vdc.network.model.types.EventFilter
import com.treintaYTres.vdc.repository.EventRepository
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(
    private val eventRepository: EventRepository
) {
    suspend fun execute(filter: EventFilter) = eventRepository.getEvents(filter)
}