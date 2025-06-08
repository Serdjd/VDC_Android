package com.treintaYTres.vdc.ui.screen.authscreen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import com.treintaYTres.vdc.network.Result
import com.treintaYTres.vdc.network.model.request.CompleteRegistryRequest
import com.treintaYTres.vdc.ui.component.InstrumentChips
import com.treintaYTres.vdc.ui.component.OutlinedTextField
import com.treintaYTres.vdc.ui.component.ProfileUri
import com.treintaYTres.vdc.ui.mapper.toChips
import com.treintaYTres.vdc.ui.model.Chip
import com.treintaYTres.vdc.ui.model.people.Instrument
import com.treintaYTres.vdc.ui.theme.VdcTheme
import com.treintaYTres.vdc.util.toFile
import com.treintaYTres.vdc.viewmodel.CompleteRegistryViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@Composable
fun CompleteRegistryScreen(
    viewModel: CompleteRegistryViewModel,
    stateHandle: SavedStateHandle?,
    navigateToInstruments: (List<Instrument>) -> Unit,
    navigateToHome: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    var imageUri by rememberSaveable {
        mutableStateOf<Uri?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        imageUri = it
    }

    val instruments = stateHandle
        ?.getStateFlow<List<Instrument>>(
            "instruments",
            listOf()
        )?.collectAsState()

    val chips = instruments?.value?.toChips() ?: listOf()

    var name by rememberSaveable { mutableStateOf("") }


    var isFormEnable by remember { mutableStateOf(true) }
    var isLoading by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(scope) {
        viewModel.registryResult.collectLatest {
            when(it) {
                is Result.Loading -> {
                    isLoading = true
                    isFormEnable = false
                }
                is Result.Success -> {
                    navigateToHome.invoke()
                }
                is Result.Error -> {
                    isLoading = false
                    isFormEnable = true
                }
            }
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ElevatedCard {
                Column(
                    modifier = Modifier
                        .padding(32.dp),
                    verticalArrangement = Arrangement.spacedBy(
                        space = 16.dp,
                        alignment = Alignment.CenterVertically
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProfileUri(
                        uri = imageUri,
                        size = 120,
                        modifier = Modifier.clickable { launcher.launch("image/*") }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        name,
                        "Nombre",
                        Icons.Rounded.Person
                    ) { name = it }
                    InstrumentChips(chips) {
                        navigateToInstruments.invoke(instruments?.value ?: emptyList())
                    }
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            if (name.isNotEmpty() && instruments?.value?.isNotEmpty() == true && imageUri != null) {
                                val file = imageUri!!.toFile(context)
                                file?.let {
                                    val pId = instruments.value[0].id
                                    val ids = instruments.value
                                        .subList(1,instruments.value.size)
                                        .map { it.id }
                                    viewModel.completeRegistry(
                                        it,
                                        CompleteRegistryRequest(
                                            ids,
                                            pId,
                                            name
                                        )
                                    )
                                }
                            } else {
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        "Faltan datos por rellenar",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }
                        }
                    ) {
                        Text("Completar registro")
                    }
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
fun CompleteRegistryPrev() {
    VdcTheme {
        //CompleteRegistryScreen(null)
    }
}

