package com.pdmproyecto.mymedicine.ui.screens.FormTest

import android.util.Log
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import java.util.Calendar

class MedicineFormViewModel: ViewModel() {

    val numKeyboardConfig = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)

    val dosisUnitList = listOf<String>("--","UI", "U", "mg", "ml")
    val timeLapUnitList = listOf<String>("horas", "dias", "semanas", "meses")


    val undefinedDurationCheck = mutableStateOf(false)

    val medicineName = mutableStateOf("")
    val dosisAmount = mutableStateOf("")
    val timeLapDuration = mutableStateOf("")

    var dosisDuration = mutableListOf<Int>(0,0,0)
    val dosisDurationString = mutableStateListOf<String>("","","") // days months years

    val startHourSelected = mutableStateListOf<String>("","") //hours minutes
    val startDateSelected = mutableStateListOf<String>("","","")//day month year
    val finishDateSelected = mutableStateListOf<String>("","","")

    val dosisUnitSelected = mutableStateOf(dosisUnitList[0])
    val timeLapUnitSelected = mutableStateOf(timeLapUnitList[0])

    fun debugShowMedicInfo(){
        Log.d(
            "MEDICINA CREADA",
            "${medicineName.value} ${dosisAmount.value} ${dosisUnitSelected.value}"
        )
        Log.d(
            "LAPSO CREADO",
            "${timeLapDuration.value} ${timeLapUnitSelected.value}"
        )

        Log.d(
            "DURACIÓN CREADA",
            "Fecha Inicial: ${startDateSelected[0]}-${startDateSelected[1]}-${startDateSelected[2]} por ${dosisDurationString[0]} dias, ${dosisDurationString[1]} meses, ${dosisDurationString[2]} años"
        )
    }

    fun convertStringToFloat(string: String): Float{
        if (string.isDigitsOnly()) return string.toFloat()
        return 0f
    }

    fun convertStringToInt(string: String): Int{
        if (string.isDigitsOnly()) return string.toInt()
        return 0
    }

    fun updateDuration(dayString: String?, monthString: String?, yearString: String?) {
        val day = if (dayString == null || dayString.isBlank()) 0
        else if (dayString.isDigitsOnly()) dayString.toInt()
        else dosisDurationString[0].toIntOrNull() ?: 0

        val month = if (monthString == null || monthString.isBlank()) 0
        else if (monthString.isDigitsOnly()) monthString.toInt()
        else dosisDurationString[1].toIntOrNull() ?: 0

        val year = if (yearString == null || yearString.isBlank()) 0
        else if (yearString.isDigitsOnly()) yearString.toInt()
        else dosisDurationString[2].toIntOrNull() ?: 0

        dosisDuration = mutableListOf(day, month, year)

        dosisDurationString[0] = if (day == 0) "" else day.toString()
        dosisDurationString[1] = if (month == 0) "" else month.toString()
        dosisDurationString[2] = if (year == 0) "" else year.toString()

        setFinishDate()
    }




    fun setFinishDate(){
        val days = dosisDuration[0]
        val months = dosisDuration[1]
        val years = dosisDuration[2]

        val calendar: Calendar = Calendar.getInstance()

        calendar.set(Calendar.DAY_OF_MONTH, startDateSelected[0].toInt())
        calendar.set(Calendar.MONTH, startDateSelected[1].toInt()-1)
        calendar.set(Calendar.YEAR, startDateSelected[2].toInt())

        calendar.add(Calendar.DAY_OF_MONTH, days)
        calendar.add(Calendar.MONTH, months)
        calendar.add(Calendar.YEAR, years)

        finishDateSelected[0] = calendar.get(Calendar.DAY_OF_MONTH).toString()
        finishDateSelected[1] = (calendar.get(Calendar.MONTH) + 1).toString()
        finishDateSelected[2] = calendar.get(Calendar.YEAR).toString()
    }

}