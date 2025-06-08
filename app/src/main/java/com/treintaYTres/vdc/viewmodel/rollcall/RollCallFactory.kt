package com.treintaYTres.vdc.viewmodel.rollcall

import dagger.assisted.AssistedFactory

@AssistedFactory
interface RollCallFactory {
    fun create(id: Int): RollCallViewModel
}