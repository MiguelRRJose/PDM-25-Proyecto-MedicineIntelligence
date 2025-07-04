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
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdmproyecto.mymedicine.MyMedicineApplication
import com.pdmproyecto.mymedicine.data.models.Medicine
import com.pdmproyecto.mymedicine.data.models.Patient
import com.pdmproyecto.mymedicine.data.models.User
import com.pdmproyecto.mymedicine.data.repositories.medicine.MedicineRepositoryInterface
import com.pdmproyecto.mymedicine.data.repositories.patient.PatientRepositoryInterface
import com.pdmproyecto.mymedicine.data.repositories.user.UserRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date

class MedicineFormViewModel(
    private val medicineRepository: MedicineRepositoryInterface,
    private val userRepository: UserRepositoryInterface,
    private val patientRepository: PatientRepositoryInterface
) : ViewModel() {

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MyMedicineApplication)
                MedicineFormViewModel(
                    application.appProvider.provideMedicineRepository(),
                    application.appProvider.provideUserRepository(),
                    application.appProvider.providePatientRepository()
                )
            }
        }
    }

    // ✅ patientId dinámico
    private var currentPatientId: Int = 1
    fun setPatientId(id: Int) {
        currentPatientId = id
    }

    val numKeyboardConfig = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
    val dosisUnitList = listOf("--", "UI", "U", "mg", "ml")
    val timeLapUnitList = listOf("horas", "dias", "semanas", "meses")

    val undefinedDurationCheck = mutableStateOf(false)
    val medicineName = mutableStateOf("")
    val dosisAmount = mutableStateOf("")
    val timeLapDuration = mutableStateOf("")

    var dosisDuration = mutableListOf(0, 0, 0)
    val dosisDurationString = mutableStateListOf("", "", "")
    val startHourSelected = mutableStateListOf("", "")
    val startDateSelected = mutableStateListOf("", "", "")
    val finishDateSelected = mutableStateListOf("", "", "")

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
        userId = 1,
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

    suspend fun insertDummyUser() = withContext(Dispatchers.IO) {
        val existingUser = userRepository.getUserFromId(1)
        if (existingUser == null) userRepository.addUser(dummyUser)

        val existingPatient = patientRepository.getPatientFromUserId(1)
        if (existingPatient == null) patientRepository.addPatient(dummyPatient)
    }

    fun debugShowMedicInfo() {
        Log.d("MEDICINA CREADA", "${medicineName.value} ${dosisAmount.value} ${dosisUnitSelected.value}")
        Log.d("LAPSO CREADO", "${timeLapDuration.value} ${timeLapUnitSelected.value}")
        Log.d(
            "DURACIÓN CREADA",
            "Fecha Inicial: ${startDateSelected[0]}-${startDateSelected[1]}-${startDateSelected[2]} por ${dosisDurationString[0]} días, ${dosisDurationString[1]} meses, ${dosisDurationString[2]} años"
        )
    }

    fun addMedicine() {
        val startCalendar = Calendar.getInstance().apply {
            set(
                startDateSelected[2].toInt(),
                startDateSelected[1].toInt() - 1,
                startDateSelected[0].toInt(),
                startHourSelected[0].toInt(),
                startHourSelected[1].toInt()
            )
        }

        val finishCalendar = if (!undefinedDurationCheck.value) {
            Calendar.getInstance().apply {
                set(
                    finishDateSelected[2].toInt(),
                    finishDateSelected[1].toInt() - 1,
                    finishDateSelected[0].toInt()
                )
            }
        } else null

        viewModelScope.launch {
            val newMedicine = Medicine(
                id = 0,
                patientId = currentPatientId,
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

    fun convertStringToFloat(string: String): Float {
        return if (string.isDigitsOnly()) string.toFloat() else 0f
    }

    fun convertStringToInt(string: String): Int {
        return if (string.isDigitsOnly()) string.toInt() else 0
    }

    fun updateDuration(dayString: String = "", monthString: String = "", yearString: String = "") {
        val day = dayString.toIntOrNull() ?: 0
        val month = monthString.toIntOrNull() ?: 0
        val year = yearString.toIntOrNull() ?: 0

        dosisDuration = mutableListOf(day, month, year)

        dosisDurationString[0] = if (day == 0) "" else day.toString()
        dosisDurationString[1] = if (month == 0) "" else month.toString()
        dosisDurationString[2] = if (year == 0) "" else year.toString()

        setFinishDate()
    }

    fun setFinishDate() {
        val calendar = Calendar.getInstance().apply {
            set(
                startDateSelected[2].toInt(),
                startDateSelected[1].toInt() - 1,
                startDateSelected[0].toInt()
            )
            add(Calendar.YEAR, dosisDuration[2])
            add(Calendar.MONTH, dosisDuration[1])
            add(Calendar.DAY_OF_MONTH, dosisDuration[0])
        }

        if (dosisDuration.any { it > 0 }) {
            finishDateSelected[0] = calendar.get(Calendar.DAY_OF_MONTH).toString()
            finishDateSelected[1] = (calendar.get(Calendar.MONTH) + 1).toString()
            finishDateSelected[2] = calendar.get(Calendar.YEAR).toString()
        } else {
            finishDateSelected[0] = startDateSelected[0]
            finishDateSelected[1] = startDateSelected[1]
            finishDateSelected[2] = startDateSelected[2]
        }
    }

    fun compareStartAndFinishDate() {
        val calendar1 = Calendar.getInstance().apply {
            set(startDateSelected[2].toInt(), startDateSelected[1].toInt() - 1, startDateSelected[0].toInt())
        }

        val calendar2 = Calendar.getInstance().apply {
            set(finishDateSelected[2].toInt(), finishDateSelected[1].toInt() - 1, finishDateSelected[0].toInt())
        }

        if (calendar2.timeInMillis < calendar1.timeInMillis) {
            finishDateSelected[0] = startDateSelected[0]
            finishDateSelected[1] = startDateSelected[1]
            finishDateSelected[2] = startDateSelected[2]
        }
    }
}
