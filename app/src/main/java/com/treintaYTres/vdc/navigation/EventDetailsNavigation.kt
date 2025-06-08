package com.treintaYTres.vdc.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.treintaYTres.vdc.navigation.screen.Screen
import com.treintaYTres.vdc.ui.model.event.EventDetails
import com.treintaYTres.vdc.ui.screen.eventdetails.EventAttendanceScreen
import com.treintaYTres.vdc.ui.screen.eventdetails.EventDetailsScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun EventDetailsNavigation(
    modifier: Modifier,
    navController: NavHostController,
    previousNavController: NavHostController,
    data: EventDetails,
    isAdmin: Boolean,
    selected: MutableIntState,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.EventDetails.Details.route,
        modifier = modifier
    ) {
        composable(
            route = Screen.EventDetails.Details.route,
            enterTransition = {
                when(initialState.destination.route) {
                    Screen.Root.EventDetails.route -> fadeIn()
                    Screen.EventDetails.Attendance.route -> slideInHorizontally { -it }
                    else -> fadeIn()
                }
            },
            exitTransition = {
                slideOutHorizontally { -it }
            }
        ) {
            EventDetailsScreen(
                data.event,
                isAdmin,
                {
                    selected.intValue = 1
                    navController.navigate(Screen.EventDetails.Attendance.route)
                },
                {
                    previousNavController.navigate("${Screen.Root.RollCall.route}/${data.event.id}")
                }
            )
        }
        composable(
            route = Screen.EventDetails.Attendance.route,
            enterTransition = {
                slideInHorizontally { it }
            },
            exitTransition = {
                slideOutHorizontally { it }
            }
        ) {
            EventAttendanceScreen(data.details)
        }
    }
}