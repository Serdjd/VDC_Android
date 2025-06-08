package com.treintaYTres.vdc.ui.model

import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavIcon(
    open val description: String,
    open val route: String?
) {
    open var navigate: () -> Unit = {}

    data class Drawable(
        val selected: Int,
        val unSelected: Int? = null,
        override val description: String,
        override val route: String
    ) : NavIcon(description,route)

    data class Url(
        var selected: String,
        override val description: String,
        override val route: String
    ) : NavIcon(description,route)

    data class Back(
        val image: ImageVector,
        override var navigate: () -> Unit
    ) : NavIcon("Back Navigation Icon",null)
}