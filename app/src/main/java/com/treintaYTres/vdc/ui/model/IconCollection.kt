package com.treintaYTres.vdc.ui.model

import androidx.navigation.NavController

class IconCollection (
    val navIcons: List<NavIcon>,
    navController: NavController
) {
    init {
        navIcons.forEach {
            it.route?.let { route->
                it.navigate = { navController.navigate(route) }
            }
        }
    }
}
