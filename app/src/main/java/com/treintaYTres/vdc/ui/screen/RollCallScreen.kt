package com.treintaYTres.vdc.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.treintaYTres.vdc.ui.component.RollCallItem
import com.treintaYTres.vdc.ui.component.TopBar
import com.treintaYTres.vdc.ui.model.Action
import com.treintaYTres.vdc.ui.model.NavIcon
import com.treintaYTres.vdc.ui.model.people.Person

@Composable
fun RollCallScreen(
    members: List<Person>,
    navigateBack: () -> Unit
) {
    val assistance = remember { mutableMapOf<Int, Boolean>() }
    Scaffold(
        topBar = {
            TopBar(
                title = "Pasar Lista",
                navIcon = NavIcon.Back(Icons.AutoMirrored.Rounded.ArrowBack,navigateBack),
                actions = listOf(
                    Action.Text("Guardar") {
                        navigateBack()
                    }
                )
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(members) {person ->
                RollCallItem(person) {
                    assistance.put(person.id,it)
                }
            }
        }
    }
}