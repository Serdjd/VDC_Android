package com.treintaYTres.vdc.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.treintaYTres.vdc.ui.model.Chip
import com.treintaYTres.vdc.ui.model.Icon
import com.treintaYTres.vdc.ui.model.InstrumentChip
import com.treintaYTres.vdc.ui.theme.VdcTheme

/**
 * A group of filter chips used when you want elements to expand horizontally
 * if they do not fit in a single row
 */
@Composable
fun FlowFilterChipGroup(
    chips: List<Chip>,
    isFirstSelected: Boolean = false
) {
    var selected by remember {
        mutableStateOf(if (isFirstSelected) chips[0] else "")
    }
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = 6.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(
            space = 16.dp
        )
    ) {
        chips.forEach {
            FilterChip(
                title = it.title,
                selected = selected == it
            ) {
                if (selected != it) {
                    selected = it
                    it.onClick.invoke()
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun FlowFilterChipGroupPrev() {
    VdcTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            FlowFilterChipGroup(
                chips = listOf(
                    Chip("All") {},
                    Chip("Performances") {},
                    Chip("Rehearsal") {},
                    Chip("Rehearsal") {}
                ),
                isFirstSelected = true
            )
        }

    }
}

/**
 * A Filter Chip Group used when elements do not go out of row
 */
@Composable
fun FilterChipGroup(
    chips: List<Chip>,
    alignment: Alignment.Horizontal,
    isFirstSelected: Boolean = false
) {
    var selected by rememberSaveable {
        mutableStateOf(if (isFirstSelected) chips[0] else "")
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = alignment
        )
    ) {
        chips.forEach {
            FilterChip(
                title = it.title,
                selected = selected == it
            ) {
                if (selected != it) {
                    selected = it
                    it.onClick.invoke()
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun FilterChipGroupPrev() {
    VdcTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            FilterChipGroup(
                chips = listOf(
                    Chip("All") {},
                    Chip("Performances") {},
                    Chip("Rehearsal") {},
                ),
                alignment = Alignment.Start,
                isFirstSelected = true
            )
        }

    }
}

/**
 * A group of filter chips for two items centered and occupying the entire space
 */
@Composable
fun PairedFilterChipGroup(
    firstChip: Chip,
    secondChip: Chip,
    isFirstSelected: Boolean = true
) {
    val titles = listOf(firstChip, secondChip)
    var selected by remember {
        mutableStateOf(if (isFirstSelected) titles[0] else "")
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterHorizontally
        )
    ) {
        titles.forEach {
            FilterChip(
                modifier = Modifier.weight(1f),
                title = it.title,
                selected = selected == it
            ) {
                if (selected != it) {
                    selected = it
                    it.onClick.invoke()
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PairedFilterChipGroupPrev() {
    VdcTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            PairedFilterChipGroup(
                firstChip = Chip("Cuerdas") {},
                secondChip = Chip("Asistencia") {},
                isFirstSelected = true
            )
        }

    }
}

/**
 * A group of filter chips with a horizontal scroll
 */
@Composable
fun ScrollableFilterChipGroup(
    chips: List<Chip>,
    isFirstSelected: Boolean = false
) {
    var selected by remember {
        mutableStateOf(if (isFirstSelected) chips[0] else "")
    }
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            16.dp,
            Alignment.CenterHorizontally
        )
    ) {
        items(chips) {
            val icon = it.icon?.let {
                Icon.DrawableIcon(it)
            }
            FilterChip(
                title = it.title,
                icon = icon,
                selected = selected == it
            ) {
                if (selected != it) {
                    selected = it
                    it.onClick.invoke()
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ScrollableFilterChipGroupPrev() {
    VdcTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            ScrollableFilterChipGroup(
                chips = listOf(
                    Chip("All") {},
                    Chip("Performances") {},
                    Chip("Rehearsal") {},
                    Chip("Rehearsal") {}
                ),
                isFirstSelected = true
            )
        }

    }
}

@Composable
fun InstrumentChips(
    chips: List<InstrumentChip>,
    onClick: () -> Unit
) {
    FlowRow(
        verticalArrangement = Arrangement.spacedBy(
            space = 6.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(
            space = 16.dp
        )
    ) {
        if (chips.isNotEmpty()) {
            chips.forEach {
                FilterChip(
                    title = it.title,
                    url = it.icon,
                    selected = chips[0].title == it.title
                ) {}
            }
        }
        FilledIconButton(
            onClick = onClick,
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = Icons.Rounded.Edit,
                contentDescription = "Edit Instruments"
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun InstrumentChipsPrev() {
    VdcTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InstrumentChips(
                chips = listOf()
            ) {

            }
        }

    }
}