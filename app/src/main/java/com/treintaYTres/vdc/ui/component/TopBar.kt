package com.treintaYTres.vdc.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.treintaYTres.vdc.R
import com.treintaYTres.vdc.ui.model.Action
import com.treintaYTres.vdc.ui.model.NavIcon
import com.treintaYTres.vdc.ui.model.Tab
import com.treintaYTres.vdc.ui.model.people.Instrument
import com.treintaYTres.vdc.ui.theme.VdcTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    navIcon: NavIcon.Back? = null,
    actions: List<Action>? = null,
    insets: WindowInsets = TopAppBarDefaults.windowInsets
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            if (navIcon != null) {
                IconButton(onClick = navIcon.navigate) {
                    Icon(
                        imageVector = navIcon.image,
                        contentDescription = "Navigation Icon"
                    )
                }

            }
        },
        actions = {
            actions?.let {
                it.forEach {
                    when (it) {
                        is Action.Icon -> {
                            IconButton(onClick = it.action) {
                                when(it.icon) {
                                    is ImageVector -> Icon(
                                        imageVector = it.icon as ImageVector,
                                        contentDescription = null
                                    )
                                    else -> Icon(
                                        painter = rememberAsyncImagePainter(model = it.icon),
                                        contentDescription = null
                                    )
                                }
                            }
                        }

                        is Action.Text -> {
                            TextButton(onClick = it.action) {
                                Text(it.text)
                            }
                        }
                    }
                }
            }
        },
        windowInsets = insets
    )
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7
)
@Composable
fun TopBarPrev() {
    VdcTheme {
        TopBar("Events")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenteredTopBar(
    insets: WindowInsets = TopAppBarDefaults.windowInsets,
    content: @Composable (() -> Unit)
) {
    CenterAlignedTopAppBar(
        title = content,
        windowInsets = insets
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7
)
@Composable
fun CenteredTopBarPrev() {
    VdcTheme {
        CenteredTopBar {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediumTopBar(
    title: String,
    navIcon: NavIcon.Back? = null,
    actions: List<Action>? = null,
    insets: WindowInsets = TopAppBarDefaults.windowInsets
) {
    MediumTopAppBar(
        title = { Text(title) },
        navigationIcon = {
            if (navIcon != null) {
                IconButton(onClick = navIcon.navigate) {
                    Icon(
                        imageVector = navIcon.image,
                        contentDescription = "Navigation Icon"
                    )
                }
            }
        },
        actions = {
            actions?.let {
                it.forEach {
                    when (it) {
                        is Action.Icon -> {
                            IconButton(onClick = it.action) {
                                Icon(
                                    painter = rememberAsyncImagePainter(model = it.icon),
                                    contentDescription = null
                                )
                            }
                        }

                        is Action.Text -> {
                            TextButton(onClick = it.action) {
                                Text(it.text)
                            }
                        }
                    }
                }
            }
        },
        windowInsets = insets
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7
)
@Composable
fun MediumTopBarPrev() {
    VdcTheme {
        MediumTopBar("Events")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabTopBar(
    title: String,
    tabs: List<Tab>,
    selected: MutableIntState,
    navIcon: NavIcon.Back? = null,
    actions: List<Action>? = null,
    insets: WindowInsets = TopAppBarDefaults.windowInsets
) {
    Column {
        TopBar(title, navIcon, actions, insets)
        TabRow(tabs,selected)
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7
)
@Composable
fun TabTopBarPrev() {
    VdcTheme {
        TabTopBar(
            "Events",
            listOf(
                Tab("Details") {},
                Tab("Members") {}
            ),
            remember { mutableIntStateOf(0) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SegmentedButtonTopBar(
    title: String,
    options: List<Action.Text>,
    navIcon: NavIcon.Back? = null,
    actions: List<Action>? = null,
    insets: WindowInsets = TopAppBarDefaults.windowInsets
) {
    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ) {
        TopBar(title, navIcon, actions, insets)
        SingleSegmentedButton(options, 16)
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7
)
@Composable
fun SegmentedButtonTopBarPrev() {
    VdcTheme {
        SegmentedButtonTopBar(
            "Events",
            listOf(
                Action.Text("Next") {},
                Action.Text("Previous") {}
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    title: String,
    placeholder: String,
    navIcon: NavIcon.Back? = null,
    actions: List<Action>? = null,
    insets: WindowInsets = TopAppBarDefaults.windowInsets
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(title, navIcon, actions, insets)
        Search(placeholder) { }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7
)
@Composable
fun SearchTopBarPrev() {
    VdcTheme {
        SearchTopBar("Choose a Instrument", "Find a Instrument")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldTopBar(
    title: String,
    placeholder: String,
    state: String,
    instruments: List<Instrument>,
    trailingIcon: ImageVector,
    navIcon: NavIcon.Back? = null,
    actions: List<Action>? = null,
    insets: WindowInsets = TopAppBarDefaults.windowInsets,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MediumTopBar(title, navIcon, actions, insets)
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            state = state,
            placeholder = placeholder,
            icon = trailingIcon,
            onValueChange = onValueChange
        )
//        LazyRow {
//            itemsIndexed(instruments) { index, instrument ->
//                InstrumentOutlined(
//                    url = instrument.url,
//                    size = 48,
//                    isPrimary = index == 0
//                )
//            }
//        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7
)
@Composable
fun OutlinedTextFieldTopBarPrev() {
    VdcTheme {
        OutlinedTextFieldTopBar(
            "Choose an Instrument",
            "Find an Instrument",
            "",
            listOf(),
            Icons.Rounded.Search,
            navIcon = NavIcon.Back(Icons.AutoMirrored.Rounded.ArrowBack) {},
            actions = listOf(Action.Text("Guardar") {})
        ) {

        }
    }
}

@Composable
fun LogoTopBar() {
    CenteredTopBar {
        Row (
            modifier = Modifier.padding(bottom = 8.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = 8.dp,
                alignment = Alignment.CenterHorizontally
            ),
        ) {
            Image(
                modifier = Modifier.size(64.dp),
                painter = painterResource(R.drawable.logo_virgen_castillo),
                contentDescription = "Logo",
            )
            Text(text = "Banda Virgen del Castillo")
        }

    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7
)
@Composable
fun LogoTopBarPrev() {
    VdcTheme {
        LogoTopBar()
    }
}
