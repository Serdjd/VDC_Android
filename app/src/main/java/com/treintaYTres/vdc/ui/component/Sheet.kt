package com.treintaYTres.vdc.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.treintaYTres.vdc.ui.model.people.Person
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sheet(
    texts: List<String>,
    actions: List<(() -> Unit)>,
    member: Person,
    onDismiss: () -> Unit,
    sheetState: SheetState
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            verticalArrangement = Arrangement.spacedBy(
                90.dp,
                Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MemberItem(member = member)

            Column {
                texts.forEachIndexed { index, text ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .clickable {
                                actions[index].invoke()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        TextHeader(text)
                        if (index < texts.lastIndex)
                            HorizontalDivider(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .align(Alignment.BottomCenter)
                            )
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalSheet(
    member: Person,
    actions: List<(() -> Unit)>,
    sheetState: SheetState,
    onDismiss: () -> Unit
) {
    val scope = rememberCoroutineScope()
    Sheet(
        texts = listOf(
            "Cesar Administrador",
            "Editar Instrumentos",
            "Eliminar Miembro"
        ),
        actions = actions,
        member = member,
        onDismiss = {
            scope.launch {
                sheetState.hide()
            }.invokeOnCompletion { onDismiss() }
        },
        sheetState = sheetState
    )
}
