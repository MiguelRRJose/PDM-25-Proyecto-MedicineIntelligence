package com.ayala.monitor_dream.Composables

import android.app.TimePickerDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun showTimePickerDialog(
    hour: Int,
    minute: Int,
    onTimeSelected: (Int, Int) -> Unit
) {
    val context = LocalContext.current

    val timePickerDialog = remember {
        TimePickerDialog(
            context,
            { _, selectedHour, selectedMinute ->
                onTimeSelected(selectedHour, selectedMinute)
            },
            hour,
            minute,
            false // Usa formato de 12 horas; cambia a true para 24h
        )
    }

    LaunchedEffect(Unit) {
        timePickerDialog.show()
    }
}
