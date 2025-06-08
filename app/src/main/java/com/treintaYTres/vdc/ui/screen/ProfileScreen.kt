package com.treintaYTres.vdc.ui.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import com.treintaYTres.vdc.network.Result
import com.treintaYTres.vdc.ui.component.InstrumentChips
import com.treintaYTres.vdc.ui.component.ProfileUri
import com.treintaYTres.vdc.ui.component.StatCard
import com.treintaYTres.vdc.ui.mapper.toChips
import com.treintaYTres.vdc.ui.model.localUiController
import com.treintaYTres.vdc.ui.model.people.Instrument
import com.treintaYTres.vdc.ui.theme.VdcTheme
import com.treintaYTres.vdc.util.toFile
import com.treintaYTres.vdc.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    stateHandle: SavedStateHandle?,
    navigateToInstruments: (List<Instrument>) -> Unit
) {

    val profile = viewModel.profile.collectAsState()
    val context = LocalContext.current

    val uiController = localUiController.current

    val imageUri = remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        it?.let {
            imageUri.value = it
            val file = it.toFile(context)
            file?.let { viewModel.updatePhoto(it) }
        }
    }

    val instruments = stateHandle
        ?.getStateFlow<List<Instrument>>(
            "instruments",
            listOf()
        )?.collectAsState()

    LaunchedEffect(Unit) {
        uiController.clearTopBar()
        uiController.clearFab()
    }

    when (profile.value) {
        is Result.Success<*> -> with((profile.value as Result.Success).data) {
            if (instruments?.value?.isNotEmpty() == true) {
                this.instruments = instruments.value
                viewModel.updateInstruments(instruments.value)
            }
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(
                            vertical = WindowInsets.systemBars.asPaddingValues()
                                .calculateTopPadding()
                        ),
                        verticalArrangement = Arrangement.spacedBy(
                            space = 16.dp,
                            alignment = Alignment.CenterVertically
                        ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ProfileUri(
                            uri = imageUri.value ?: url,
                            size = 120,
                            modifier = Modifier.clickable {
                                launcher.launch("image/*")
                            }
                        )
                        Text(
                            text = name,
                            style = MaterialTheme.typography.titleLarge
                        )
                        InstrumentChips(this@with.instruments.toChips()) {
                            navigateToInstruments.invoke(this@with.instruments)
                        }
                    }
                }
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(32.dp)
                ) {
                    Text(
                        "Mis Estadísticas",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxWidth(),
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(stats) {
                            StatCard(
                                it
                            )
                        }
                    }
                }

            }
        }

        else -> {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ProfileScreenPrev() {
    VdcTheme {
        ProfileScreen(
            hiltViewModel(),
            null
        ) {

        }
    }
}