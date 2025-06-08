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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.treintaYTres.vdc.ui.model.event.Member
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sheet(
    data: Map<() -> Unit, String>,
    member: Member,
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
                45.dp,
                Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SheetMemberItem(member = member)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                data.entries.forEachIndexed { index, entry ->
                    Box(
                        modifier = Modifier
                            .height(50.dp)
                            .clickable {
                                entry.key.invoke()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = entry.value,
                            style = MaterialTheme.typography.titleLarge
                        )
                        if (index < data.entries.size - 1)
                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth(.6f)
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
    userId: Int,
    member: Member,
    actions: List<(() -> Unit)>,
    sheetState: SheetState,
    onDismiss: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val list = mutableMapOf<() -> Unit, String>(
        actions[1] to "Editar Instrumentos"
    )
    if (member.id != userId) {
        if (member.isAdmin.value) list.put(actions[0], "Cesar Administrador")
        list.put(actions[2], "Eliminar Miembro")
    }


    Sheet(
        data = list,
        member = member,
        onDismiss = {
            scope.launch {
                sheetState.hide()
            }.invokeOnCompletion { onDismiss() }
        },
        sheetState = sheetState
    )
}
