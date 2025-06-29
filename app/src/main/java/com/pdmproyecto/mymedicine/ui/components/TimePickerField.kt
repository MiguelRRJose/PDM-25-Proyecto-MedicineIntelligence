package com.pdmproyecto.mymedicine.ui.components

import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.pdmproyecto.mymedicine.ui.theme.DarkGreen
import java.util.Calendar

@Composable
fun TimePickerField(
    context: Context,
    selectedTime: SnapshotStateList<String> // ["HH", "mm"]
) {
    val calendar = Calendar.getInstance()

    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    val timePicker = TimePickerDialog(
        context,
        { _, selectedHour, selectedMinute ->
            selectedTime[0] = selectedHour.toString().padStart(2, '0')
            selectedTime[1] = selectedMinute.toString().padStart(2, '0')
        },
        hour,
        minute,
        true // true para formato 24h, false para 12h
    )

    val textTime = remember(selectedTime[0], selectedTime[1]) {
        if (selectedTime.all { it.isNotBlank() }) "${selectedTime[0]}:${selectedTime[1]}"
        else "Seleccionar hora"
    }

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { timePicker.show() },
        colors = ButtonDefaults.buttonColors(
            containerColor = DarkGreen,
            contentColor = Color.White
        )
    ) {
        Text(text = textTime, fontSize = 20.sp)
    }

    // Al iniciar se setea la hora actual si está en blanco
    LaunchedEffect(Unit) {
        if (selectedTime.all { it.isBlank() }) {
            selectedTime[0] = hour.toString().padStart(2, '0')
            selectedTime[1] = minute.toString().padStart(2, '0')
        }
    }
}
