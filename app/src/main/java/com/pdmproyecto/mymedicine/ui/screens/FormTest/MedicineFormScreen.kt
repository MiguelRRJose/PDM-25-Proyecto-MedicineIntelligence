package com.pdmproyecto.mymedicine.ui.screens.FormTest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdmproyecto.mymedicine.ui.components.DatePickerField
import com.pdmproyecto.mymedicine.ui.components.ExposedDropdownMenu
import com.pdmproyecto.mymedicine.ui.components.TextInput
import com.pdmproyecto.mymedicine.ui.components.TimePickerField
import com.pdmproyecto.mymedicine.ui.theme.DarkGreen

@Composable
fun MedicineFormScreen(viewModel: MedicineFormViewModel = viewModel(factory = MedicineFormViewModel.Factory)){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        DosisInputSection(viewModel)

        TimeLapseInputSection(viewModel)

        StartDateInputSection(viewModel)
        FinishDateInputSection(viewModel)

        StartHourSection(viewModel)

        DurationInputSection(viewModel)


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
                onClick = {

                    viewModel.onConfirmClick()
                }
            ) {
                Text("CONFIRMAR")
            }
        }
    }
}


@Composable
fun DosisInputSection(
    viewModel: MedicineFormViewModel
){
    Column(
        modifier = Modifier.padding(vertical = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextInput(
            modifier = Modifier.fillMaxWidth(),
            label = "Medicamento",
            value = viewModel.medicineName.value,
            onChange = {
                viewModel.medicineName.value = it
            }
        )

        Text(text = "Dosis", fontWeight = FontWeight.Bold)

        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ){

            TextInput(
                modifier = Modifier.weight(1f),
                label = "Cantidad",
                value = viewModel.dosisAmount.value,
                onChange = {

                    if (it.matches(Regex("^\\d*\\.\\d*$"))){
                        if (it.length <= 7){
                            viewModel.dosisAmount.value = it
                        }
                    }
                    if (it.matches(Regex("^\\d*"))){
                        if (it.length <= 6){
                            viewModel.dosisAmount.value = it
                        }
                    }

                },
                keyboardOptions = viewModel.numKeyboardConfig
            )

            ExposedDropdownMenu(
                modifier = Modifier.weight(1f),
                items = viewModel.dosisUnitList,
                selectedItem = viewModel.dosisUnitSelected.value,
                onItemSelection = {
                    viewModel.dosisUnitSelected.value = it
                }
            )

        }

    }
}


@Composable
fun TimeLapseInputSection(
    viewModel: MedicineFormViewModel
){
    Column(
        modifier = Modifier.padding(vertical = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Lapso entre cada dosis", fontWeight = FontWeight.Bold)

        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ){

            TextInput(
                modifier = Modifier.weight(1f),
                label = "Lapso",
                value = viewModel.timeLapDuration.value,
                onChange = {
                    if (it.isDigitsOnly() || it.isEmpty()){
                        if (it.length <= 2) {
                            viewModel.timeLapDuration.value = it
                        }
                    }
                },
                keyboardOptions = viewModel.numKeyboardConfig
            )

            ExposedDropdownMenu(
                modifier = Modifier.weight(1f),
                items = viewModel.timeLapUnitList,
                selectedItem = viewModel.timeLapUnitSelected.value,
                onItemSelection = {
                    viewModel.timeLapUnitSelected.value = it
                }
            )

        }
    }
}


@Composable
fun StartDateInputSection(
    viewModel: MedicineFormViewModel
){
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
            selectedDate = viewModel.startDateSelected,
            onDateSelected = {
                viewModel.compareStartAndFinishDate()
                viewModel.setFinishDate()
            }
        )
    }
}


@Composable
fun FinishDateInputSection(
    viewModel: MedicineFormViewModel
){
    Row(
        modifier = Modifier.padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(0.5f),
            text = "Fecha final:",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        DatePickerField(
            context = LocalContext.current,
            enabled = !viewModel.undefinedDurationCheck.value,
            selectedDate = viewModel.finishDateSelected,
            onDateSelected =
                {
                    viewModel.dosisDurationString[0] = ""
                    viewModel.dosisDurationString[1] = ""
                    viewModel.dosisDurationString[2] = ""
                    viewModel.dosisDuration[0] = 0
                    viewModel.dosisDuration[1] = 0
                    viewModel.dosisDuration[2] = 0

                },
            minDateList = viewModel.startDateSelected
        )
    }
}



@Composable
fun StartHourSection(
    viewModel: MedicineFormViewModel
){
    Row(
        modifier = Modifier.padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(0.5f),
            text = "Hora a iniciar:",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        TimePickerField(
            context = LocalContext.current,
            selectedTime = viewModel.startHourSelected,
        )
    }
}

@Composable
fun DurationInputSection(
    viewModel: MedicineFormViewModel
){
    Column (
        modifier = Modifier.padding(vertical = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Duración de la medicación", fontWeight = FontWeight.Bold)

        DurationPicker(
            enabled = !viewModel.undefinedDurationCheck.value,
            viewModel = viewModel
        )

        Row (verticalAlignment = Alignment.CenterVertically){
            Checkbox(
                checked = viewModel.undefinedDurationCheck.value,
                onCheckedChange = { viewModel.undefinedDurationCheck.value = it},
            )
            Text(text = "Rango indefinido")
        }

    }
}



@Composable
fun DurationPicker(
    enabled: Boolean = true,
    viewModel: MedicineFormViewModel
){


    LaunchedEffect(enabled) {
        if (!enabled){
            viewModel.dosisDurationString[0] = ""
            viewModel.dosisDurationString[1] = ""
            viewModel.dosisDurationString[2] = ""
        }
    }

    Row (
        modifier = Modifier.fillMaxWidth(0.9f),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        TextInput(
            modifier = Modifier.weight(1f),
            label = "Dias",
            value = viewModel.dosisDurationString[0],
            enabled = enabled,
            onChange = {
                if (it.isDigitsOnly() || it.isEmpty()){
                    if (it.length <= 3) {
                        viewModel.updateDuration(it,viewModel.dosisDurationString[1],viewModel.dosisDurationString[2])
                    }
                }
            },
            keyboardOptions = viewModel.numKeyboardConfig
        )

        TextInput(
            modifier = Modifier.weight(1f),
            label = "Meses",
            value = viewModel.dosisDurationString[1],
            enabled = enabled,
            onChange = {
                if (it.isDigitsOnly() || it.isEmpty()){
                    if (it.length <= 2) {
                        viewModel.updateDuration(viewModel.dosisDurationString[0],it,viewModel.dosisDurationString[2])
                    }
                }
            },
            keyboardOptions = viewModel.numKeyboardConfig
        )

        TextInput(
            modifier = Modifier.weight(1f),
            label = "Años",
            value = viewModel.dosisDurationString[2],
            enabled = enabled,
            onChange = {
                if (it.isDigitsOnly() || it.isEmpty()){
                    if (it.length <= 2) {
                        viewModel.updateDuration(viewModel.dosisDurationString[0],viewModel.dosisDurationString[1],it)
                    }
                }
            },
            keyboardOptions = viewModel.numKeyboardConfig
        )

    }
}