package com.pdmproyecto.mymedicine.ui.screens.FormTest

import android.util.Log
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdmproyecto.mymedicine.MyMedicineApplication
import com.pdmproyecto.mymedicine.data.models.Medicine
import com.pdmproyecto.mymedicine.data.models.Patient
import com.pdmproyecto.mymedicine.data.models.User
import com.pdmproyecto.mymedicine.data.repositories.medicine.MedicineRepositoryInterface
import com.pdmproyecto.mymedicine.data.repositories.patient.PatientRepositoryInterface
import com.pdmproyecto.mymedicine.data.repositories.user.UserRepository
import com.pdmproyecto.mymedicine.data.repositories.user.UserRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date

class MedicineFormViewModel(private val medicineRepository: MedicineRepositoryInterface, private val userRepository: UserRepositoryInterface, private val patientRepository: PatientRepositoryInterface): ViewModel() {


    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory{
            initializer {
                val application = (this[APPLICATION_KEY] as MyMedicineApplication)
                MedicineFormViewModel(application.appProvider.provideMedicineRepository(), application.appProvider.provideUserRepository(), application.appProvider.providePatientRepository())
            }
        }
    }



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

    val dummyUser = User(
        id = 1,
        name = "Dummy User",
        email = "dummy@example.com",
        phoneNumber = "00000000",
        dui = "00000000-0",
        age = 30,
        password = "dummy"
    )

    val dummyPatient = Patient(
        id = 1,
        userId = 1,  // cualquier userId válido o dummy
        water = 0,
        sleepHours = 0f,
        sleepAlarmHour = Date(),
        weight = 0f,
        steps = 0
    )

    fun onConfirmClick() {
        viewModelScope.launch {
            insertDummyUser()

            addMedicine()
        }
    }

    suspend fun insertDummyUser() = withContext(Dispatchers.IO){

        val existingUser = userRepository.getUserFromId(1)
        if (existingUser == null) userRepository.addUser(dummyUser)
        val existingPatient = patientRepository.getPatientFromUserId(1)
        if  (existingPatient == null) patientRepository.addPatient(dummyPatient)
    }


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

    fun addMedicine(){
        val startCalendar = Calendar.getInstance()
        startCalendar.set(
            startDateSelected[2].toInt(),
            startDateSelected[1].toInt() - 1,
            startDateSelected[0].toInt(),
            startHourSelected[0].toInt(),
            startHourSelected[1].toInt()
        )


        val finishCalendar = if(!undefinedDurationCheck.value){
            val calendar = Calendar.getInstance()
            calendar.set(
                finishDateSelected[2].toInt(),
                finishDateSelected[1].toInt() - 1,
                finishDateSelected[0].toInt()
            )
            calendar
        } else {
            null
        }


        viewModelScope.launch {
            var newMedicine = Medicine(
            id = 0,
            patientId = 1,
            name = medicineName.value,
            unit = dosisUnitSelected.value,
            amount = dosisAmount.value.toFloat(),
            startDate = startCalendar.time,
            finishDate = finishCalendar?.time,
            timeLap = timeLapDuration.value.toInt(),
            timeLapUnit = timeLapUnitSelected.value
            )

            medicineRepository.addMedicine(newMedicine)
        }

    }



    fun convertStringToFloat(string: String): Float{
        if (string.isDigitsOnly()) return string.toFloat()
        return 0f
    }

    fun convertStringToInt(string: String): Int{
        if (string.isDigitsOnly()) return string.toInt()
        return 0
    }

    fun updateDuration(dayString: String = "", monthString: String = "", yearString: String = "") {
        var day = 0
        var month = 0
        var year = 0

        if (dayString.isNotBlank() && dayString.isDigitsOnly()) day = dayString.toInt()
        if (monthString.isNotBlank() && monthString.isDigitsOnly()) month = monthString.toInt()
        if (yearString.isNotBlank() && yearString.isDigitsOnly()) year = yearString.toInt()

        dosisDuration = mutableListOf(day, month, year)

        dosisDurationString[0] = if (day == 0) "" else day.toString()
        dosisDurationString[1] = if (month == 0) "" else month.toString()
        dosisDurationString[2] = if (year == 0) "" else year.toString()

        setFinishDate()
    }

    fun setFinishDate(){

        var days = dosisDuration[0]
        var months = dosisDuration[1]
        var years = dosisDuration[2]


        val calendar: Calendar = Calendar.getInstance()
        calendar.set(startDateSelected[2].toInt(), startDateSelected[1].toInt()-1, startDateSelected[0].toInt())

        calendar.add(Calendar.YEAR, years)
        calendar.add(Calendar.MONTH, months)
        calendar.add(Calendar.DAY_OF_MONTH, days)

        if (days > 0 || months > 0 || years > 0){
            finishDateSelected[0] = calendar.get(Calendar.DAY_OF_MONTH).toString()
            finishDateSelected[1] = (calendar.get(Calendar.MONTH) + 1).toString()
            finishDateSelected[2] = calendar.get(Calendar.YEAR).toString()
        } else {

            finishDateSelected[0] = startDateSelected[0]
            finishDateSelected[1] = startDateSelected[1]
            finishDateSelected[2] = startDateSelected[2]

        }

    }

    fun compareStartAndFinishDate(){
        val calendar1 = Calendar.getInstance()
        val calendar2 = Calendar.getInstance()

        calendar1.set(startDateSelected[2].toInt(), startDateSelected[1].toInt() - 1, startDateSelected[0].toInt())
        calendar2.set(finishDateSelected[2].toInt(), finishDateSelected[1].toInt() - 1, finishDateSelected[0].toInt())

        if (calendar2.timeInMillis < calendar1.timeInMillis){

            finishDateSelected[0] = startDateSelected[0]
            finishDateSelected[1] = startDateSelected[1]
            finishDateSelected[2] = startDateSelected[2]
        }
    }

}