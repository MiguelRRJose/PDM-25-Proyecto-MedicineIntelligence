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
    enabled: Boolean = true,
    selectedDate: SnapshotStateList<String>, //permite pasar una lista editable como parametro
    onDateSelected: () -> Unit = {},
    minDateList: List<String>? = null,
    maxDateList: List<String>? = null
){

    val calendar = Calendar.getInstance()

    var year = calendar.get(Calendar.YEAR)
    var month = calendar.get(Calendar.MONTH)
    var day = calendar.get(Calendar.DAY_OF_MONTH)

    if (selectedDate.all { it -> it.isNotBlank() }){
        year = selectedDate[2].toInt()
        month = selectedDate[1].toInt() - 1
        day = selectedDate[0].toInt()
    }


    val datePicker = DatePickerDialog(
        context,
        {
                _, yearSelected, monthSelected, daySelected ->
            selectedDate[0] = daySelected.toString()
            selectedDate[1] = (monthSelected + 1).toString()
            selectedDate[2] = yearSelected.toString()
            onDateSelected()
        },
        year, month, day
    )

    if (minDateList?.size == 3 && minDateList.all { it -> it.isNotBlank() }){
        val minCalendar = Calendar.getInstance()
        minCalendar.set(minDateList[2].toInt(), minDateList[1].toInt() - 1, minDateList[0].toInt())
        datePicker.apply { datePicker.datePicker.minDate = minCalendar.timeInMillis }
    }

    if (maxDateList?.size == 3 && maxDateList.all { it -> it.isNotBlank() }){
        val maxCalendar = Calendar.getInstance()
        maxCalendar.set(maxDateList[2].toInt(), maxDateList[1].toInt() - 1, maxDateList[0].toInt())
        datePicker.apply { datePicker.datePicker.maxDate = maxCalendar.timeInMillis}
    }

    //al usar parametros en remember, se recalculara al cambiar los valores de estos, SnapshotStateList es necesario para que esto funcione
    val textDate = remember(selectedDate[0], selectedDate[1], selectedDate[2], enabled){
        if (selectedDate.all { it.isNotBlank() } && enabled) "${selectedDate[0]}-${selectedDate[1]}-${selectedDate[2]}"
        else "Sin Fecha"
    }

    Button(
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled,
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
            selectedDate[1] = (month + 1).toString() //corrección de més de 0-11 a 1-12
            selectedDate[2] = year.toString()
        }
    }
}