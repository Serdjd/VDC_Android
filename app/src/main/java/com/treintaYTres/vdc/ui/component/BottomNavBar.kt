package com.treintaYTres.vdc.ui.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.treintaYTres.vdc.R
import com.treintaYTres.vdc.navigation.screen.Screen
import com.treintaYTres.vdc.ui.model.NavIcon
import com.treintaYTres.vdc.ui.theme.VdcTheme

@Composable
fun BottomNavBar(
    navIcons: List<NavIcon>,
    firstSelected: Int,
) {
    var selected by rememberSaveable { mutableIntStateOf(firstSelected) }

    NavigationBar {
        navIcons.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    when(item) {
                        is NavIcon.Drawable -> {
                            Icon(
                                painter = painterResource(if (selected == index) item.selected else item.unSelected ?: item.selected) ,
                                contentDescription = item.description
                            )
                        }
                        is NavIcon.Url -> {
                            AsyncImage(
                                model = item.selected,
                                contentDescription = item.description
                            )
                        }
                        else -> {}
                    }
                },
                selected = selected == index,
                onClick = {
                    selected = index
                    item.navigate()
                }
            )
        }
    }

}

@Preview
@Composable
fun BottomNavBarPrev() {
    val icons = listOf(
        NavIcon.Drawable(
            R.drawable.event_filled,
            R.drawable.event_outlined,
            "Event Icon",
            ""
        ),
        NavIcon.Drawable(
            R.drawable.home_filled,
            R.drawable.home_outlined,
            "Home Icon",
            Screen.Root.Band.route
        ),
        NavIcon.Drawable(
            R.drawable.account_filled,
            R.drawable.account_outlined,
            "Profile Icon",
            ""
        )
    )
    VdcTheme {
        BottomNavBar(icons,1)
    }
}
