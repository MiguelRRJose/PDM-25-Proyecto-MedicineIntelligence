package com.ayala.monitor_dream.composables

import android.app.TimePickerDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar

@Composable
fun ShowTimePickerDialog(
    hour: Int,
    minute: Int,
    onTimeSelected: (Int, Int) -> Unit
) {
    val context = LocalContext.current

    val timePickerDialog = remember {
        TimePickerDialog(
            context, { _, selectedHour, selectedMinute ->
                onTimeSelected(selectedHour, selectedMinute) }, hour, minute, false
        )
    }

    LaunchedEffect(Unit) {
        timePickerDialog.show()
    }
}

@Composable
fun ShowReminderPickerDialog(
    minute: Int,
    onTimeSelected: (Int) -> Unit
) {
    val context = LocalContext.current

    val currentHour = remember { Calendar.getInstance().get(Calendar.HOUR_OF_DAY) }

    val timePickerDialog = remember {
        TimePickerDialog(
            context,
            { _, _, selectedMinute ->
                onTimeSelected(selectedMinute)
            },
            currentHour,
            minute,
            false
        )
    }
    LaunchedEffect(Unit) {
        timePickerDialog.show()
    }
}
