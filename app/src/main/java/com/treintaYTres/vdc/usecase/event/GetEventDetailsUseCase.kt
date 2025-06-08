package com.treintaYTres.vdc.usecase.event

import com.treintaYTres.vdc.repository.EventRepository
import javax.inject.Inject

class GetEventDetailsUseCase @Inject constructor(
    private val eventRepository: EventRepository
) {
    suspend fun execute(id: Int) = eventRepository.getEventDetails(id)
}