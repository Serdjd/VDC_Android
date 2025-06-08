package com.treintaYTres.vdc.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.treintaYTres.vdc.navigation.screen.Screen
import com.treintaYTres.vdc.ui.model.auth.Validation
import com.treintaYTres.vdc.ui.model.people.Instrument
import com.treintaYTres.vdc.ui.screen.CreateEventScreen
import com.treintaYTres.vdc.ui.screen.MainScreen
import com.treintaYTres.vdc.ui.screen.RollCallScreen
import com.treintaYTres.vdc.ui.screen.SelectInstrumentScreen
import com.treintaYTres.vdc.ui.screen.SplashScreen
import com.treintaYTres.vdc.ui.screen.authscreen.CompleteRegistryScreen
import com.treintaYTres.vdc.ui.screen.authscreen.LoginScreen
import com.treintaYTres.vdc.ui.screen.authscreen.SignUpScreen
import com.treintaYTres.vdc.ui.screen.authscreen.WaitingScreen
import com.treintaYTres.vdc.ui.screen.eventdetails.EventDetailsLayout
import com.treintaYTres.vdc.util.navigate
import com.treintaYTres.vdc.viewmodel.eventdetails.EventDetailsFactory
import com.treintaYTres.vdc.viewmodel.rollcall.RollCallFactory

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RootNavigation(
    modifier: Modifier,
    navController: NavHostController
) {
    var validation by remember {
        mutableStateOf(
            Validation(
                false,
                false,
                false
            )
        )
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Root.Splash.route,
        modifier = modifier
    ) {
        composable(Screen.Root.Splash.route) {
            SplashScreen(hiltViewModel()) {
                validation = it
                navController.navigate(validation.navigate())
            }
        }

        composable(
            route = Screen.Root.Waiting.route,
            enterTransition = {
                when(initialState.destination.route) {
                    Screen.Root.SignUp.route -> slideInVertically { it }
                    else -> fadeIn()
                }
            },
        ) {
            WaitingScreen()
        }

        composable(
            route = Screen.Root.Login.route,
            enterTransition = {
                when(initialState.destination.route) {
                    Screen.Root.SignUp.route -> slideInHorizontally { -it }
                    else -> fadeIn()
                }
            },
            exitTransition = {
                when(targetState.destination.route) {
                    Screen.Root.SignUp.route -> slideOutHorizontally { -it }
                    Screen.Root.Waiting.route,
                    Screen.Root.CompleteRegistry.route -> slideOutVertically { it }
                    else -> fadeOut()
                }
            }
        ) {
            LoginScreen(
                hiltViewModel(),
                {
                    validation = it
                    navController.navigate(validation.navigate())
                }
            ) {
                navController.navigate(Screen.Root.SignUp.route)
            }
        }

        composable(
            route = Screen.Root.SignUp.route,
            enterTransition = {
                when(initialState.destination.route) {
                    Screen.Root.Login.route -> slideInHorizontally { it }
                    else -> fadeIn()
                }
            },
            exitTransition = {
                when(targetState.destination.route) {
                    Screen.Root.Login.route -> slideOutHorizontally { it }
                    Screen.Root.Waiting.route,
                    Screen.Root.CompleteRegistry.route -> slideOutVertically { it }
                    else -> fadeOut()
                }
            }
        ) {
            SignUpScreen(
                hiltViewModel(),
                {
                    navController.navigate(Screen.Root.Waiting.route)
                }
            ) {
                navController.navigate(Screen.Root.Login.route)
            }
        }

        composable(
            route = Screen.Root.NewEvent.route,
            enterTransition = {
                slideInVertically { it }
            },
            popExitTransition = {
                slideOutVertically { it }
            }
        ) {
            CreateEventScreen(
                hiltViewModel(),
                {
                    navController.previousBackStackEntry?.savedStateHandle["update"] = true
                    navController.popBackStack()
                }
            ) {
                navController.previousBackStackEntry?.savedStateHandle["update"] = true
                navController.popBackStack()
            }
        }

        composable(
            route = "${Screen.Root.EventDetails.route}/{id}?type={type}&isAdmin={isAdmin}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                },
                navArgument("type") {
                    type = NavType.StringType
                },
                navArgument("isAdmin") {
                    type = NavType.StringType
                }
            ),
            enterTransition = {
                scaleIn()
            },
            popExitTransition = {
                scaleOut()
            }
        ) {

            val id = it.arguments?.getInt("id") ?: -1
            val type = it.arguments?.getString("type") ?: ""
            val isAdmin = it.arguments?.getBoolean("isAdmin") == true

            EventDetailsLayout(
                hiltViewModel(
                    creationCallback = { factory: EventDetailsFactory ->
                        factory.create(id)
                    }
                ),
                title = type,
                isAdmin = isAdmin,
                previousNavController = navController
            ) {
                navController.popBackStack()
            }
        }

        composable(
            route = Screen.Root.CompleteRegistry.route,
            enterTransition = {
                when(initialState.destination.route) {
                    Screen.Root.SignUp.route,
                    Screen.Root.Login.route -> slideInVertically { -it }
                    else -> fadeIn()
                }
            },
            exitTransition = {
                fadeOut()
            }
        ) {

            CompleteRegistryScreen(
                hiltViewModel(),
                it.savedStateHandle,
                navigateToInstruments = { list ->
                    it.savedStateHandle["selected_instruments"] = list
                    navController.navigate(Screen.Root.SelectInstrument.route)
                }
            ) {
                navController.navigate(Screen.Root.Main.route)
            }
        }

        composable(Screen.Root.Main.route) {
            MainScreen(
                hiltViewModel(),
                navController
            )
        }

        composable(Screen.Root.SelectInstrument.route) {
            SelectInstrumentScreen(
                hiltViewModel(),
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<List<Instrument>>("selected_instruments")
                    ?: listOf(),
                {
                    navController.popBackStack()
                }
            ) { list ->
                navController.previousBackStackEntry?.savedStateHandle["instruments"] = list
                navController.popBackStack()
            }
        }

        composable(
            route = "${Screen.Root.RollCall.route}/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) {
            val id = it.arguments?.getInt("id") ?: -1

            RollCallScreen(
                hiltViewModel(
                    creationCallback = { factory: RollCallFactory ->
                        factory.create(id)
                    }
                )
            ) {
                navController.popBackStack()
            }
        }
    }
}