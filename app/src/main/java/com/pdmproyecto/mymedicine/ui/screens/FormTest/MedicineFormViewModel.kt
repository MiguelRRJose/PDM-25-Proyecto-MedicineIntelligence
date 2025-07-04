package com.pdmproyecto.mymedicine.ui.screens.FormTest

import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
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
import androidx.core.net.toUri

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

    var toastMessage = mutableStateOf<String?>(null)


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


    fun checkAndRequestExactAlarmPermission(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val hasPermission = alarmManager.canScheduleExactAlarms()

            if (!hasPermission) {
                val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
                    data = "package:${context.packageName}".toUri()
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(intent)
            }
        }
    }



    fun validateFields(): Boolean {
        if (medicineName.value.isBlank()) {
            toastMessage.value = "Por favor, ingresa el nombre del medicamento"
            return false
        }
        if (dosisAmount.value.isBlank()) {
            toastMessage.value = "Por favor, ingresa la dosis"
            return false
        }
        if (dosisUnitSelected.value == dosisUnitList[0]) {
            toastMessage.value = "Por favor, selecciona la unidad de dosis"
            return false
        }
        if (timeLapDuration.value.isBlank()) {
            toastMessage.value = "Por favor, ingresa la duración del lapso"
            return false
        }

        toastMessage.value = null
        return true
    }

    fun onConfirmClick(onSuccess: () -> Unit) {
        if (!validateFields()) return

        viewModelScope.launch {
            insertDummyUser()
            addMedicine(onSuccess)
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
        Log.d(
            "FECHA FINAL",
            "${finishDateSelected[0]}-${finishDateSelected[1]}-${finishDateSelected[2]}"
        )
    }

    fun addMedicine(onSuccess: ()-> Unit) {
        try {
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
                debugShowMedicInfo()
                medicineRepository.addMedicine(newMedicine)
                onSuccess()
        }

        } catch (e: Exception){
            toastMessage.value = "No se pudo guardar la medicina por algún error :("
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
