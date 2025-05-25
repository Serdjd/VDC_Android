package com.treintaYTres.vdc.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.treintaYTres.vdc.ui.component.InstrumentChips
import com.treintaYTres.vdc.ui.component.Profile

@Composable
fun ProfileScreen(
    id: Int
) {
    val scrollState = rememberScrollState()
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(scrollState)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = WindowInsets.systemBars.asPaddingValues().calculateTopPadding())
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        space = 16.dp,
                        alignment = Alignment.CenterVertically
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
//                    Profile(
//                        url = url,
//                        size = 120
//                    )
//                    Text(text = name, style = MaterialTheme.typography.titleLarge)
//                    InstrumentChips() {
//
//                    }
                }
            }
        }
    }
}