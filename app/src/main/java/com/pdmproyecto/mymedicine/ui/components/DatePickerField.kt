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
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import java.util.Calendar
import com.pdmproyecto.mymedicine.ui.theme.DarkGreen

@Composable
fun DatePickerField(
    context: Context,
    selectedDate: SnapshotStateList<String> //permite pasar una lista editable como parametro
){

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)


    val datePicker = DatePickerDialog(
        context,
        {
                _, yearSelected, monthSelected, daySelected ->
            selectedDate[0] = daySelected.toString()
            selectedDate[1] = (monthSelected + 1).toString()
            selectedDate[2] = yearSelected.toString()
        },
        year, month, day
    )

    //al usar parametros en remember, se recalculara al cambiar los valores de estos, SnapshotStateList es necesario para que esto funcione
    val textDate = remember(selectedDate[0], selectedDate[1], selectedDate[2]){
        if (selectedDate.all { it.isNotBlank() }) "${selectedDate[0]}-${selectedDate[1]}-${selectedDate[2]}"
        else "Seleccionar Fecha"
    }

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {datePicker.show()},
        colors = ButtonDefaults.buttonColors(
            backgroundColor = DarkGreen,
            contentColor = Color.White
        )
    ) {

        Text(text = textDate, fontSize = 20.sp)
        Icon(imageVector = Icons.Default.DateRange, contentDescription = "acceder a calendario")
    }

    //Al iniciar se setean los valores como los de la fecha actual capturada por calendar
    LaunchedEffect(Unit) {
        if (selectedDate.all { it.isBlank() }){
            selectedDate[0] = day.toString()
            selectedDate[1] = month.toString()
            selectedDate[2] = year.toString()
        }
    }
}