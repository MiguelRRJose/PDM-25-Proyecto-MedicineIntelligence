package com.pdmproyecto.mymedicine.ui.screens.MedicineAlarms

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.pdmproyecto.mymedicine.R
import com.pdmproyecto.mymedicine.data.models.Medicine
import com.pdmproyecto.mymedicine.ui.components.ConfirmationPopUp
import com.pdmproyecto.mymedicine.ui.components.MedicineCard
import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.BottomNavigationBar
import com.pdmproyecto.mymedicine.ui.theme.DarkPalidGreen

@Composable
fun MedicineAlarmsScreen(
    username: String,
    navController: NavHostController,
    viewModel: MedicineAlarmsViewModel = viewModel(factory = MedicineAlarmsViewModel.Factory)
) {
    val medicineList = viewModel.medicineList.collectAsState(initial = emptyList())
    val popUpVisibility = remember { mutableStateOf(false) }
    val medicineToDelete = remember { mutableStateOf(viewModel.setMedicineToGeneric()) }
    val roundedShape = RoundedCornerShape(15.dp)

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "medicina"

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(
                username = username,
                currentRoute = navController.currentBackStackEntry?.destination?.route ?: "",
                onItemSelected = { newRoute ->
                    if (newRoute != currentRoute) {
                        navController.navigate(newRoute) {
                            popUpTo("dashboard/$username") { inclusive = false }
                        }
                    }
                },
                onCentralActionClick = { /* AI */ }
            )

        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.logo1),
                    contentDescription = "Logo MI",
                    modifier = Modifier.size(100.dp)
                )

                Text(
                    text = "My Medicine",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.80f)
                        .background(color = DarkPalidGreen, shape = roundedShape)
                        .padding(10.dp)
                ) {

                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(medicineList.value) { medicine ->
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

            ConfirmationPopUp(
                text = "¿Desea eliminar ${medicineToDelete.value.name} de la lista?",
                isVisible = popUpVisibility.value,
                confirmAction = {
                    viewModel.removeMedicine(medicineToDelete.value)
                    popUpVisibility.value = false
                },
                cancelAction = {
                    medicineToDelete.value = viewModel.setMedicineToGeneric()
                    popUpVisibility.value = false
                }
            )
        }
    }
}
