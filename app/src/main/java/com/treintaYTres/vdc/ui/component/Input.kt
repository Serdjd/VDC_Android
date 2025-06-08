package com.treintaYTres.vdc.ui.component

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDialog
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.treintaYTres.vdc.R
import com.treintaYTres.vdc.ui.theme.VdcTheme
import com.treintaYTres.vdc.ui.util.formatTime
import com.treintaYTres.vdc.ui.util.getTodayFormatted
import com.treintaYTres.vdc.ui.util.toDate
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateInput(
    onValueChange: (String) -> Unit
) {
    var showModal by remember { mutableStateOf(false) }
    var date by remember { mutableStateOf(getTodayFormatted()) }

    LaunchedEffect(date) {
        onValueChange(date)
    }

    OutlinedTextField(
        modifier = Modifier
            .wrapContentWidth()
            .pointerInput(date) {
                awaitEachGesture {
                    awaitFirstDown(pass = PointerEventPass.Initial)
                    val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                    if (upEvent != null) {
                        showModal = true
                    }
                }
            },
        state = date,
        placeholder = "Día",
        icon = Icons.Rounded.DateRange,
        readOnly = true,
        onValueChange = {}
    )

    if (showModal) {
        DateModal(
            onDateSelected = {
                it?.let {
                    date = it.toDate()
                }
            },
            onDismiss = {
                showModal = false
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val date = rememberDatePickerState()
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected.invoke(date.selectedDateMillis)
                onDismiss.invoke()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = date)
    }
}

@Preview(
    showBackground = true
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateInputPrev() {
    VdcTheme {
        DateInput() {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeInput(
    onValueChange: (String) -> Unit
) {
    var showModal by remember { mutableStateOf(false) }
    val current = Calendar.getInstance()
    val hour = remember { mutableIntStateOf(current.get(Calendar.HOUR_OF_DAY)) }
    val minute = remember { mutableIntStateOf(current.get(Calendar.MINUTE)) }
    val hourState = remember {
        derivedStateOf {
            "${hour.intValue.formatTime()}:${minute.intValue.formatTime()}"
        }
    }
    LaunchedEffect(hourState.value) {
        onValueChange(hourState.value)
    }

    OutlinedTextField(
        modifier = Modifier
            .wrapContentWidth()
            .pointerInput(hourState) {
                awaitEachGesture {
                    awaitFirstDown(pass = PointerEventPass.Initial)
                    val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                    if (upEvent != null) {
                        showModal = true
                    }
                }
            },
        state = hourState,
        placeholder = "Hora",
        icon = painterResource(R.drawable.schedule),
        readOnly = true,
        onValueChange = {}
    )

    if (showModal) {
        TimeModal(
            onDateSelected = {
                it.let {
                    hour.intValue = it.first
                    minute.intValue = it.second
                }
            },
            onDismiss = {
                showModal = false
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeModal(
    onDateSelected: (Pair<Int, Int>) -> Unit,
    onDismiss: () -> Unit
) {
    val current = Calendar.getInstance()
    val time = rememberTimePickerState(
        initialHour = current.get(Calendar.HOUR_OF_DAY),
        initialMinute = current.get(Calendar.MINUTE),
        is24Hour = true
    )
    TimePickerDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Select a hour")
        },
        confirmButton = {
            TextButton(onClick = {
                onDateSelected.invoke(Pair(time.hour, time.minute))
                onDismiss.invoke()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        TimePicker(state = time)
    }
}

@Preview(
    showBackground = true
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeInputPrev() {
    VdcTheme {
        TimeInput { }
    }
}