package com.treintaYTres.vdc.ui.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.treintaYTres.vdc.navigation.MainNavigation
import com.treintaYTres.vdc.navigation.RootNavigation
import com.treintaYTres.vdc.navigation.screen.Screen
import com.treintaYTres.vdc.ui.component.BottomNavBar
import com.treintaYTres.vdc.ui.model.IconCollection
import com.treintaYTres.vdc.ui.model.NavIcon
import com.treintaYTres.vdc.ui.model.UiController
import com.treintaYTres.vdc.ui.model.localUiController
import com.treintaYTres.vdc.ui.util.Constant
import com.treintaYTres.vdc.viewmodel.MainViewModel
import javax.inject.Inject

@OptIn(ExperimentalAnimationApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    rootNavController: NavHostController
) {

    val navController = rememberNavController()
    val uiController = remember { UiController() }

    val icons = Constant.Icons.navIcons.apply {
        (this.last() as NavIcon.Url).selected = viewModel.getProfileUrl()
    }

    CompositionLocalProvider(localUiController provides uiController) {
        Scaffold(
            topBar = {
                val topbar = uiController.topBarState.topBar.value
                AnimatedContent(
                    targetState = topbar,
                    transitionSpec = {
                        (fadeIn() + slideInHorizontally { it }).togetherWith(fadeOut() + slideOutHorizontally { it })
                    }
                ) {
                    it?.invoke()
                }


            },
            bottomBar = {
                BottomNavBar(
                    navIcons = remember {
                        IconCollection(icons,navController)
                    }.navIcons,
                    firstSelected = 1
                )
            },
            floatingActionButton = {
                val fab = uiController.fabState.fab.value
                AnimatedContent(
                    targetState = fab,
                    transitionSpec = {
                        (fadeIn() + slideInHorizontally { it }).togetherWith(fadeOut() + slideOutHorizontally { it })
                    }
                ) {
                    it?.invoke()
                }
            }
        ) {
            MainNavigation(
                modifier = Modifier
                    .padding(it),
                navController,
                rootNavController
            )
        }
    }

}