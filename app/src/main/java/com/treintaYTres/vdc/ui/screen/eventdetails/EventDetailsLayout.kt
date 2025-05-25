package com.treintaYTres.vdc.ui.screen.eventdetails

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.treintaYTres.vdc.navigation.EventDetailsNavigation
import com.treintaYTres.vdc.navigation.screen.Screen
import com.treintaYTres.vdc.ui.component.TabTopBar
import com.treintaYTres.vdc.ui.model.NavIcon
import com.treintaYTres.vdc.ui.model.Tab

@Composable
fun EventDetailsLayout(
    navigateBack: () -> Unit
) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TabTopBar(
                title = "",
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
                )
            )
        }
    ) {
        EventDetailsNavigation(
            modifier = Modifier.padding(it),
            navController = navController
        )
    }
}