package com.treintaYTres.vdc.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.treintaYTres.vdc.network.Result
import com.treintaYTres.vdc.ui.component.RollCallItem
import com.treintaYTres.vdc.ui.component.TopBar
import com.treintaYTres.vdc.ui.mapper.toRollCallRequest
import com.treintaYTres.vdc.ui.model.Action
import com.treintaYTres.vdc.ui.model.NavIcon
import com.treintaYTres.vdc.viewmodel.rollcall.RollCallViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RollCallScreen(
    viewModel: RollCallViewModel,
    navigateBack: () -> Unit
) {
    val data = viewModel.data.collectAsState()
    val scope = rememberCoroutineScope()
    LaunchedEffect(scope) {
        viewModel.result.collectLatest {
            when(it) {
                is Result.Success<*> -> {
                    navigateBack()
                }
                else -> {}
            }
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "Pasar Lista",
                navIcon = NavIcon.Back(Icons.AutoMirrored.Rounded.ArrowBack,navigateBack),
                actions = listOf(
                    Action.Text("Guardar") {
                        viewModel.updateData((data.value as Result.Success).data.toRollCallRequest())
                    }
                )
            )
        }
    ) {
        when(data.value) {
            is Result.Success<*> -> with((data.value as Result.Success).data) {
                LazyColumn(
                    modifier = Modifier.padding(it)
                ) {
                    items(this@with) {item ->
                        RollCallItem(item)
                    }
                }
            }
            else -> {}
        }

    }
}