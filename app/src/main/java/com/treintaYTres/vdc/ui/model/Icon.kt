package com.treintaYTres.vdc.ui.model

import androidx.compose.ui.graphics.vector.ImageVector

sealed interface Icon {
    val contentDescription: String?

    class DrawableIcon(var resource: Int, override val contentDescription: String? = null) : Icon
    class VectorIcon(var resource: ImageVector, override val contentDescription: String? = null) : Icon
}