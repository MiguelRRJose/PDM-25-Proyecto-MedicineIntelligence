package com.pdmproyecto.mymedicine.ui.screens.FormTest

import android.util.Log
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import java.time.LocalDate

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

    fun updateDuration(dayString: String, monthString: String, yearString: String){
        var day = 0
        var month = 0
        var year = 0

        if (dayString.isNotBlank() && dayString.isDigitsOnly()) day = dayString.toInt()
        if (monthString.isNotBlank() && monthString.isDigitsOnly()) month = monthString.toInt()
        if (yearString.isNotBlank() && yearString.isDigitsOnly()) year = yearString.toInt()

        dosisDuration = mutableListOf(day, month, year)
        //sumDurationToDate(day.toLong(), month.toLong(), year.toLong())
    }


//    fun sumDurationToDate(days: Long, months: Long, years: Long){
//
//        val startDate = LocalDate.of(startDateSelected[2].toInt(), startDateSelected[1].toInt(), startDateSelected[0].toInt())
//        val finishDate = startDate
//            .plusDays(days)
//            .plusMonths(months)
//            .plusYears(years)
//
//        finishDateSelected[0] = finishDate.dayOfMonth.toString()
//        finishDateSelected[1] = finishDate.month.toString()
//        finishDateSelected[2] = finishDate.year.toString()
//    }


}