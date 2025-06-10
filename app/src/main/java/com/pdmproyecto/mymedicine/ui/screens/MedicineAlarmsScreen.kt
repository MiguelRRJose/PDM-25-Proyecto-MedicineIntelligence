package com.pdmproyecto.mymedicine.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdmproyecto.mymedicine.data.Medicine
import com.pdmproyecto.mymedicine.ui.components.ConfirmationPopUp
import com.pdmproyecto.mymedicine.ui.components.MedicineCard
import com.pdmproyecto.mymedicine.ui.theme.DarkPalidGreen

@Composable
fun MedicineAlarmsScreen(viewModel: MedicineAlarmsViewModel = viewModel()){

    val medicineList = viewModel.medicineList
    val popUpVisibility = remember{ mutableStateOf(false)}
    val medicineToDelete = remember{ mutableStateOf(Medicine(-1,"","", 2f, "","",""))}

    val roundedShape = RoundedCornerShape(15.dp)


    Box(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){

        Column(modifier = Modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Text(
                text = "My Medicine",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontSize = 25.sp,
                modifier = Modifier.padding(bottom = 20.dp))

            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.60f)
                .background(color = DarkPalidGreen, shape = roundedShape)
                .padding(10.dp)
                .border(width = 0.dp, color = DarkPalidGreen, shape = roundedShape)
            ){

                LazyColumn(modifier = Modifier
                    .fillMaxSize()
                ){

                    items(medicineList) {
                        medicine ->
                        MedicineCard(
                            medicine = medicine,
                            deleteButtonAction = {
                                medicineToDelete.value = medicine
                                popUpVisibility.value = true
                            }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                }

            }

        }


        if (popUpVisibility.value){
            Box(
                modifier = Modifier
                    .background(color = Color.Black.copy(alpha = 0.7f))
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                ConfirmationPopUp(
                    text = "Desea eliminar ${medicineToDelete.value.name} de la lista?",
                    confirmAction =
                        {
                            viewModel.removeMedicine(medicineToDelete.value)
                            popUpVisibility.value = false
                        },
                    cancelAction =
                        {
                            medicineToDelete.value = Medicine(-1,"","", 2f, "","","")
                            popUpVisibility.value = false
                        }
                )
            }
        }

    }
}