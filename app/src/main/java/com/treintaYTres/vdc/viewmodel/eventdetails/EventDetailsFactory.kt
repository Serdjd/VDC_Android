package com.treintaYTres.vdc.viewmodel.eventdetails

import dagger.assisted.AssistedFactory

@AssistedFactory
interface EventDetailsFactory {
    fun create(id: Int): EventDetailsViewModel
}