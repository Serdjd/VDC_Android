package com.treintaYTres.vdc.navigation.screen

sealed class Screen(val route: String) {

    sealed interface Root {
        object Splash: Screen("splash_screen")
        object Login: Screen("login_screen")
        object SignUp: Screen("signUp_screen")
        object Event: Screen("event_screen")
        object Band: Screen("band_screen")
        object Profile: Screen("profile_screen")
        object NewEvent: Screen("new_event_screen")
        object RollCall: Screen("roll_call_screen")
        object SelectInstrument: Screen("select_instrument_screen")
        object EventDetails: Screen("event_details_screen_route")
        object CompleteRegistry: Screen("complete_registry_screen")
        object Waiting: Screen("waiting_screen")
        object Main: Screen("main_screen")
    }

    sealed interface EventDetails {
        object Details: Screen("event_details_screen")
        object Attendance: Screen("event_attendance_screen")
    }

}