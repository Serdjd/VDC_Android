package com.treintaYTres.vdc.ui.util

import com.treintaYTres.vdc.R
import com.treintaYTres.vdc.navigation.screen.Screen
import com.treintaYTres.vdc.ui.model.Icon
import com.treintaYTres.vdc.ui.model.NavIcon
import com.treintaYTres.vdc.ui.model.TypeE
import com.treintaYTres.vdc.ui.model.profile.Type

sealed class Constant {

    class Event : Constant() {
        companion object {
            const val POSITIVE_CONFIRMATION = 0
            const val NEGATIVE_CONFIRMATION = 1
            const val PENDING_CONFIRMATION = 2
        }
    }

    class DI : Constant() {
        companion object {
            const val USER_DATA = "user_data"
        }
    }

    class SharedPrefs : Constant() {
        companion object {
            const val USER_KEY = "user_data_key"
            const val USER_ID = "user_id"
            const val USER_IS_ADMIN = "user_permissions"
            const val USER_URL = "user_url"
        }
    }

    class Icons: Constant() {

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

    class EventType: Constant() {
        companion object {
            val REHEARSAL = Type(TypeE.Rehearsal.name, Icon.DrawableIcon(R.drawable.rehearsal))
            val PERFORMANCE = Type(TypeE.Performance.name, Icon.DrawableIcon(R.drawable.performance))
        }
    }

    class Events: Constant() {
        companion object {
            val ALL = listOf(
                EventType.PERFORMANCE,
                EventType.REHEARSAL
            )
        }
    }
}