package com.treintaYTres.vdc.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.treintaYTres.vdc.navigation.RootNavigation
import com.treintaYTres.vdc.ui.component.BottomNavBar
import com.treintaYTres.vdc.ui.model.IconCollection
import com.treintaYTres.vdc.ui.model.UiController
import com.treintaYTres.vdc.ui.model.localUiController
import com.treintaYTres.vdc.ui.util.Constant

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val uiController = remember { UiController() }

    CompositionLocalProvider(localUiController provides uiController) {
        Scaffold(
            topBar = {
                uiController.topBarState.topBar?.invoke()
            },
            bottomBar = {
                BottomNavBar(
                    navIcons = remember {
                        IconCollection(Constant.Icons.navIcons,navController)
                    }.navIcons,
                    firstSelected = 1
                )
            },
            floatingActionButton = {
                uiController.fabState.fab?.invoke()
            }
        ) {
            RootNavigation(
                modifier = Modifier.padding(it),
                navController
            )
        }
    }

}