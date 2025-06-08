package com.treintaYTres.vdc.usecase.event

import com.treintaYTres.vdc.repository.EventRepository
import javax.inject.Inject

class UpdateAttendanceUseCase @Inject constructor(
    private val eventRepository: EventRepository
) {
    suspend fun execute(
        eventId: Int,
        attendance: Boolean
    ) = eventRepository.updateAttendance(eventId, attendance)
}