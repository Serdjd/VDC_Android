package com.treintaYTres.vdc.ui.model

import androidx.compose.ui.graphics.vector.ImageVector

sealed class Action(
    open val action: () -> Unit
) {
    sealed class Icon(
        open val icon: Any,
        override val action: () -> Unit
    ): Action(action) {
        data class Drawable (override val icon: Int, override val action: () -> Unit): Icon(icon,action)
        data class Vector (override val icon: ImageVector, override val action: () -> Unit): Icon(icon,action)
    }

    data class Text (val text: String, override val action: () -> Unit): Action(action)
}
