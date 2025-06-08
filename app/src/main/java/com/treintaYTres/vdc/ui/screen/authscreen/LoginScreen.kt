package com.treintaYTres.vdc.ui.screen.authscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import com.treintaYTres.vdc.network.Result
import com.treintaYTres.vdc.ui.component.OutlinedTextField
import com.treintaYTres.vdc.ui.component.TextTitle
import com.treintaYTres.vdc.ui.model.auth.Validation
import com.treintaYTres.vdc.ui.theme.VdcTheme
import com.treintaYTres.vdc.viewmodel.SignInViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: SignInViewModel,
    navigateToHomeScreen: (Validation) -> Unit,
    navigateToSignUpScreen: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var btnEnable by remember { mutableStateOf(true) }
    var isLoading by remember { mutableStateOf(false) }

    val validation = viewModel.validation.collectAsState()

    LaunchedEffect(scope) {
        viewModel.signInResult.onEach {
            when(it) {
                is Result.Success<*> -> {
                    btnEnable = true
                    isLoading = false
                    navigateToHomeScreen(validation.value)
                }
                is Result.Loading<*> -> {
                    isLoading = true
                }
                is Result.Error<*> -> {
                    btnEnable = true
                    isLoading = false
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            it.message ?: "Error occurred, tried it later",
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            }
        }.launchIn(this)
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(
                        16.dp
                    )
                ) {
                    TextTitle(
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = "Login"
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        state = email,
                        placeholder = "Email",
                        icon = Icons.Rounded.Email
                    ) {
                        email = it
                    }
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        state = password,
                        placeholder = "Password",
                        icon = Icons.Rounded.Lock
                    ) {
                        password = it
                    }
                    Column {
                        TextButton(
                            modifier = Modifier.align(Alignment.End),
                            onClick = navigateToSignUpScreen
                        ) {
                            Text("New here?")
                        }
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                viewModel.signIn(email, password)
                                btnEnable = false
                            },
                            enabled = btnEnable
                        ) {
                            if (!isLoading) {
                                Text("Login")
                            } else {
                                CircularProgressIndicator()
                            }
                        }
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
fun LoginScreenPrev() {
    VdcTheme {
        LoginScreen(hiltViewModel(),{}) {

        }
    }
}