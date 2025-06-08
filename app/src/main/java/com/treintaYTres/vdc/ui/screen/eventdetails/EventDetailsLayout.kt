package com.treintaYTres.vdc.ui.screen.eventdetails

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.treintaYTres.vdc.navigation.EventDetailsNavigation
import com.treintaYTres.vdc.navigation.screen.Screen
import com.treintaYTres.vdc.network.Result
import com.treintaYTres.vdc.ui.component.TabTopBar
import com.treintaYTres.vdc.ui.model.NavIcon
import com.treintaYTres.vdc.ui.model.Tab
import com.treintaYTres.vdc.ui.model.event.EventDetails
import com.treintaYTres.vdc.viewmodel.eventdetails.EventDetailsViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun EventDetailsLayout(
    viewModel: EventDetailsViewModel,
    title: String,
    isAdmin: Boolean,
    previousNavController: NavHostController,
    navigateBack: () -> Unit
) {
    val navController = rememberNavController()
    val data = viewModel.data.collectAsState()
    val selected = remember { mutableIntStateOf(0) }
    Scaffold(
        topBar = {
            TabTopBar(
                title = title,
                tabs = listOf(
                    Tab("Details") {
                        navController.navigate(Screen.EventDetails.Details.route)
                    },
                    Tab("Members") {
                        navController.navigate(Screen.EventDetails.Attendance.route)
                    }
                ),
                navIcon = NavIcon.Back(
                    image = Icons.AutoMirrored.Rounded.ArrowBack,
                    navigate = navigateBack
                ),
                selected = selected
            )
        }
    ) {
        when(data.value) {
            is Result.Success -> EventDetailsNavigation(
                    modifier = Modifier.padding(it),
                    navController = navController,
                    previousNavController = previousNavController,
                    data = (data.value as Result.Success<EventDetails>).data,
                    isAdmin = isAdmin,
                    selected = selected
                )
            else -> {}
        }
    }
}