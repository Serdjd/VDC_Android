package com.treintaYTres.vdc.ui.model.people

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Instrument(
    val id: Int,
    val name: String,
    val url: String
) : Parcelable
