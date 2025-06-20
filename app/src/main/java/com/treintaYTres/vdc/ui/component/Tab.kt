package com.treintaYTres.vdc.ui.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.treintaYTres.vdc.ui.model.Tab
import com.treintaYTres.vdc.ui.theme.VdcTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabRow(
    tabs: List<Tab>,
    selected: MutableIntState
) {
    SecondaryTabRow(
        selectedTabIndex = selected.intValue,
        indicator = {
            TabRowDefaults.SecondaryIndicator(
                Modifier.tabIndicatorOffset(selected.intValue,true)
            )
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = index == selected.intValue,
                onClick = {
                    if (index != selected.intValue) {
                        selected.intValue = index
                        tab.onClick()
                    }
                },
                text = { Text(tab.title) }
            )
        }
    }
}

@Preview
@Composable
fun TabRowPrev(
) {
    VdcTheme {
        TabRow(
            listOf(
                Tab("Details") {},
                Tab("Members") {}
            ),
            remember { mutableIntStateOf(0) }
        )
    }
}