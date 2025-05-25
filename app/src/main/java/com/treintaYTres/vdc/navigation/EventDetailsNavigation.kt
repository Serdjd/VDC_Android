package com.treintaYTres.vdc.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.treintaYTres.vdc.navigation.screen.Screen

@Composable
fun EventDetailsNavigation(
    modifier: Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.EventDetails.Details.route,
        modifier = modifier
    ) {
        composable(Screen.EventDetails.Details.route) {}
        composable(Screen.EventDetails.Attendance.route) {}
    }
}