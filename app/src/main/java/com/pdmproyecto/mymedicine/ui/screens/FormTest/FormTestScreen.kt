package com.pdmproyecto.mymedicine.ui.screens.FormTest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.pdmproyecto.mymedicine.ui.components.DatePickerField
import com.pdmproyecto.mymedicine.ui.components.DropdownSelector
import com.pdmproyecto.mymedicine.ui.components.TextInput
import com.pdmproyecto.mymedicine.ui.theme.DarkGreen

@Composable
fun FormTestScreen(){

    val keyboardOptionsNumeric = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)

    val undefinedDurationCheck = remember { mutableStateOf(false) }

    var medicineString = remember { mutableStateOf("") }
    var dosisString = remember { mutableStateOf("0")}
    val dosisUnits = listOf<String>("--","UI", "U", "mg", "ml")
    val selectedDosisUnits = remember{ mutableStateOf(dosisUnits[0]) }


    val timeLapString = remember { mutableStateOf("0") }
    val timeLapUnits = listOf<String>("horas", "dias", "semanas", "meses")
    val selectedTimeLapUnit = remember { mutableStateOf(timeLapUnits[0]) }


    val startDateSelected = remember { mutableStateListOf(0,0,0) } //day, month, year


    Column (modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        Column (
            modifier = Modifier.padding(vertical = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Dosis", fontWeight = FontWeight.Bold)

            TextInput(
                modifier = Modifier.fillMaxWidth(),
                label = "Medicamento",
                value = medicineString.value,
                onChange = {
                    medicineString.value = it
                }
            )

            Row(
                modifier = Modifier.padding(vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ){

                TextInput(
                    modifier = Modifier.weight(1f),
                    label = "cant. medicamento",
                    value = dosisString.value,
                    onChange = {
                        if (it.matches(Regex("^\\d*\\.?\\d*\$")) || it.isEmpty()){
                            if (it.length <= 7) dosisString.value = it
                        }
                    },
                    keyboardOptions = keyboardOptionsNumeric
                )

                DropdownSelector(
                    modifier = Modifier.weight(1f),
                    items = dosisUnits,
                    selectedItem = selectedDosisUnits.value,
                    onItemSelection = {
                        selectedDosisUnits.value = it
                    }
                )

            }

        }

        Column (
            modifier = Modifier.padding(vertical = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Lapso entre cada dosis", fontWeight = FontWeight.Bold)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ){

                TextInput(
                    modifier = Modifier.weight(1f),
                    label = "Lapso",
                    value = timeLapString.value,
                    onChange = {
                        if (it.isDigitsOnly() || it.isEmpty()){
                            if (it.length <= 2) timeLapString.value = it
                        }
                    },
                    keyboardOptions = keyboardOptionsNumeric
                )

                DropdownSelector(
                    modifier = Modifier.weight(1f),
                    items = timeLapUnits,
                    selectedItem = selectedTimeLapUnit.value,
                    onItemSelection = {
                        selectedTimeLapUnit.value = it
                    }
                )
            }
        }

        Row(
            modifier = Modifier.padding(vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(0.5f),
                text = "Fecha de Inicio:",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            DatePickerField(
                context = LocalContext.current,
                onDateSelected = {
                        day, month, year ->
                    startDateSelected[0] = day
                    startDateSelected[1] = month
                    startDateSelected[2] = year
                }
            )
        }

        Column (
            modifier = Modifier.padding(vertical = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Duración de la medicación", fontWeight = FontWeight.Bold)

            durationPicker(enabled = !undefinedDurationCheck.value);

            Row (verticalAlignment = Alignment.CenterVertically){
                Checkbox(
                    checked = undefinedDurationCheck.value,
                    onCheckedChange = { undefinedDurationCheck.value = it},
                )
                Text(text = "Rango indefinido")
            }

        }

        Row (
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ){
            Button(
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                ),
                onClick = {}
            ) {
                Text("CANCELAR")
            }

            Button(
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkGreen,
                    contentColor = Color.White
                ),
                onClick = {}
            ) {
                Text("CONFIRMAR")
            }
        }


    }
}


@Composable
fun durationPicker(enabled: Boolean = true){

    var durationString = remember { mutableStateListOf("","","") }//dias, meses, años
    val keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)

    if (!enabled){
        durationString[0] = ""
        durationString[1] = ""
        durationString[2] = ""
    }

    Row (
        modifier = Modifier.fillMaxWidth(0.9f),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        TextInput(
            modifier = Modifier.weight(1f),
            label = "Dias",
            value = durationString[0],
            enabled = enabled,
            onChange = {
                if (it.isDigitsOnly() || it.isEmpty()){
                    if (it.length <= 3) durationString[0] = it
                }
            },
            keyboardOptions = keyboardOptions
        )

        TextInput(
            modifier = Modifier.weight(1f),
            label = "Meses",
            value = durationString[1],
            enabled = enabled,
            onChange = {
                if (it.isDigitsOnly() || it.isEmpty()){
                    if (it.length <= 2) durationString[1] = it
                }
            },
            keyboardOptions = keyboardOptions
        )

        TextInput(
            modifier = Modifier.weight(1f),
            label = "Años",
            value = durationString[2],
            enabled = enabled,
            onChange = {
                if (it.isDigitsOnly() || it.isEmpty()){
                    if (it.length <= 2) durationString[2] = it
                }
            },
            keyboardOptions = keyboardOptions
        )

    }
}