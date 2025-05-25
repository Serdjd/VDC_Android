package com.treintaYTres.vdc.ui.util

import com.treintaYTres.vdc.R
import com.treintaYTres.vdc.navigation.screen.Screen
import com.treintaYTres.vdc.ui.model.NavIcon

sealed class Constant {
    class Event : Constant() {
        companion object {
            const val PENDING_CONFIRMATION = 0
            const val POSITIVE_CONFIRMATION = 1
            const val NEGATIVE_CONFIRMATION = 2
        }
    }

    class Icons : Constant() {
        companion object {
            val navIcons = listOf(
                NavIcon.Drawable(
                    R.drawable.event_filled,
                    R.drawable.event_outlined,
                    "Event Icon",
                    Screen.Root.Event.route
                ),
                NavIcon.Drawable(
                    R.drawable.home_filled,
                    R.drawable.home_outlined,
                    "Home Icon",
                    Screen.Root.Band.route
                ),
                NavIcon.Url(
                    "",
                    "Profile Icon",
                    Screen.Root.Profile.route
                )
            )
        }
    }
}