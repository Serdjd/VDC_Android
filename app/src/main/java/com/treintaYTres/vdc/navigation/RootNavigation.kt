package com.treintaYTres.vdc.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.treintaYTres.vdc.navigation.screen.Screen
import com.treintaYTres.vdc.ui.screen.SplashScreen
import com.treintaYTres.vdc.ui.screen.authscreen.LoginScreen
import com.treintaYTres.vdc.ui.screen.authscreen.SignUpScreen

@Composable
fun RootNavigation(
    modifier: Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Root.Splash.route,
        modifier = modifier
    ) {
        composable(Screen.Root.Splash.route) {
            SplashScreen(hiltViewModel()) {
                navController.navigate(
                    if (it) Screen.Root.Event.route
                    else Screen.Root.Login.route
                )
            }
        }
        composable(Screen.Root.Login.route) {
            LoginScreen(
                hiltViewModel(),
                {
                    navController.navigate(Screen.Root.Band.route)
                }
            ) {
                navController.navigate(Screen.Root.SignUp.route)
            }
        }
        composable(Screen.Root.SignUp.route) {
            SignUpScreen(
                hiltViewModel(),
                {
                    navController.navigate(Screen.Root.Band.route)
                }
            ) {
                navController.navigate(Screen.Root.Login.route)
            }
        }
        composable(Screen.Root.Event.route) {}
        composable(Screen.Root.Band.route) {}
        composable(Screen.Root.Profile.route) {}
        composable(Screen.Root.NewEvent.route) {}
        composable(Screen.Root.RollCall.route) {}
        composable(Screen.Root.Rehearsal.route) {}
        composable(Screen.Root.SelectInstrument.route) {}
    }
}