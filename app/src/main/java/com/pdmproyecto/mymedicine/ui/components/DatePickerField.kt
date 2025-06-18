package com.pdmproyecto.mymedicine.ui.components

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import java.util.Calendar
import java.util.Date
import com.pdmproyecto.mymedicine.ui.theme.DarkGreen

@Composable
fun DatePickerField(
    context: Context,
    onDateSelected: (Int, Int, Int) -> Unit,
){

    val calendar = Calendar.getInstance()

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()

    val dateText = remember { mutableStateOf("$day-${month+1}-$year") }

    val datePicker = DatePickerDialog(
        context,
        {
                _, yearSelected, monthSelected, daySelected ->
            onDateSelected(daySelected, monthSelected + 1, yearSelected)
            dateText.value = "$daySelected-${monthSelected + 1}-$yearSelected"
        },
        year, month, day
    )


    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {datePicker.show()},
        colors = ButtonDefaults.buttonColors(
            backgroundColor = DarkGreen,
            contentColor = Color.White
        )
    ) {

        Text(text = dateText.value, fontSize = 20.sp)
        Icon(imageVector = Icons.Default.DateRange, contentDescription = "acceder a calendario")
    }

    //Apenas se renderiza se cambia el texto a su
    LaunchedEffect(Unit) {
        onDateSelected(day, month + 1, year)
    }

}