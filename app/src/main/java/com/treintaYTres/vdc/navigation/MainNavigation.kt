package com.treintaYTres.vdc.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.treintaYTres.vdc.navigation.screen.Screen
import com.treintaYTres.vdc.ui.screen.BandScreen
import com.treintaYTres.vdc.ui.screen.EventScreen
import com.treintaYTres.vdc.ui.screen.ProfileScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainNavigation(
    modifier: Modifier,
    navController: NavHostController,
    rootNavController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Root.Band.route,
        modifier = modifier
    ) {

        composable(
            route = Screen.Root.Event.route,
            enterTransition = {
                slideInHorizontally { -it }
            },
            exitTransition = {
                when(targetState.destination.route) {
                    Screen.Root.Band.route -> slideOutHorizontally { -it }
                    Screen.Root.NewEvent.route -> slideOutVertically { it }
                    else -> fadeOut()
                }

            },
            popEnterTransition = {
                slideInVertically { it }
            }

        ) {
            val rootNavStack = rootNavController.currentBackStackEntry
            EventScreen(
                hiltViewModel(),
                rootNavStack?.savedStateHandle,
                { id, type, isAdmin ->
                    rootNavController.navigate("${Screen.Root.EventDetails.route}/$id?type=$type&isAdmin=$isAdmin}")
                }
            ) {
                rootNavController.navigate(Screen.Root.NewEvent.route)
            }
        }

        composable(
            route = Screen.Root.Band.route,
            enterTransition = {
                when (initialState.destination.route) {
                    Screen.Root.Event.route -> slideInHorizontally { it }
                    Screen.Root.Profile.route -> slideInHorizontally { -it }
                    else -> fadeIn()
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    Screen.Root.Event.route -> slideOutHorizontally { it }
                    Screen.Root.Profile.route -> slideOutHorizontally { -it }
                    else -> fadeOut()
                }
            }
        ) {
            val rootNavStack = rootNavController.currentBackStackEntry
            BandScreen(
                hiltViewModel(),
                rootNavStack?.savedStateHandle,
                navigateToInstruments = { list ->
                    rootNavStack?.savedStateHandle["selected_instruments"] = list
                    rootNavController.navigate(Screen.Root.SelectInstrument.route)
                }
            )
        }

        composable(
            route = Screen.Root.Profile.route,
            enterTransition = {
                slideInHorizontally { it }
            },
            exitTransition = {
                slideOutHorizontally { it }
            }
        ) {
            ProfileScreen(
                hiltViewModel(),
                rootNavController.currentBackStackEntry?.savedStateHandle
            ) { list ->
                rootNavController.currentBackStackEntry?.savedStateHandle["selected_instruments"] = list
                rootNavController.navigate(Screen.Root.SelectInstrument.route)
            }
        }

    }
}