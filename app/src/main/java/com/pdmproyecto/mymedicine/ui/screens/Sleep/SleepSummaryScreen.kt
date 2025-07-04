package com.pdmproyecto.mymedicine.ui.screens.Sleep

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.pdmproyecto.mymedicine.Composables.BarGraph
import com.pdmproyecto.mymedicine.Composables.ButtonAction
import com.pdmproyecto.mymedicine.Composables.CardIcon
import com.pdmproyecto.mymedicine.Composables.PersonalBackground
import com.pdmproyecto.mymedicine.Composables.PersonalSpacer
import com.pdmproyecto.mymedicine.Composables.TextAMPersColr
import com.pdmproyecto.mymedicine.Composables.TextEspecialPersColr
import com.pdmproyecto.mymedicine.Composables.TextSubDatePersColr
import com.pdmproyecto.mymedicine.Composables.TextSubPersColr
import com.pdmproyecto.mymedicine.Composables.TextSubPersEspecialColor
import com.pdmproyecto.mymedicine.Composables.TextTimePersColr
import com.pdmproyecto.mymedicine.Composables.TextTittlePersColr
import com.pdmproyecto.mymedicine.R
import com.pdmproyecto.mymedicine.ViewModel.DataViewModel
import com.pdmproyecto.mymedicine.ViewModel.SleepViewModel
import com.pdmproyecto.mymedicine.utils.formatTimeAMPM

@Composable
fun SleepTrackingScreen(
    navController: NavController,
    viewModel: SleepViewModel,
) {
    //Dato ViewModel
    val alarmTime = viewModel.alarmTime.collectAsState().value

    val sleepDuration = viewModel.sleepTimeDurationH.collectAsState().value

    val dateDetails = viewModel.dateDetails.collectAsState().value

    val dataViewModel: DataViewModel = viewModel()

    val totalCount by dataViewModel.totalItemCount.collectAsState()


    LaunchedEffect(Unit) {}


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        PersonalBackground(R.drawable.backgroud_1,"background")

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.TopEnd)
                .padding(top = 90.dp,end = 16.dp),
            horizontalAlignment = Alignment.End

        ) {
            TextTittlePersColr("Reporte de sueño",Color.White)

            PersonalSpacer(16)

            TextSubPersColr("Consultando tus estadisticas de sueño!",Color.White)

            PersonalSpacer(16)

            Row (

                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)

            ) {

                ButtonAction(action = "Volver", modifier = Modifier.weight(1f))
                {
                    navController.popBackStack()
                }

                ButtonAction(action = "Borrar datos", modifier = Modifier.weight(1f))
                {
                    dataViewModel.clearAllData()
                }

            }
        }

        Column(modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(640.dp)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {

                Column {

                    Row(modifier = Modifier.fillMaxWidth()) {

                        TextEspecialPersColr("Sesiones de sueño guardadas: ", Color.Black)

                        TextEspecialPersColr(totalCount.toString(), Color.Black)
                    }

                    PersonalSpacer(16)

                    TextSubDatePersColr(dateDetails.nameDay,dateDetails.dayOfMonth, dateDetails.month, dateDetails.year,
                        Color.Black)

                    PersonalSpacer(20)

                    TextAMPersColr(formatTimeAMPM(alarmTime.hour, alarmTime.minute),Color.Black)

                    PersonalSpacer(20)

                    CardIcon(R.drawable.moon_purple_2,"Dulces sueños  ",Color.Black)

                    BarGraph()

                    PersonalSpacer(30)

                    Row (modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)) {

                        TextTimePersColr(sleepDuration.hour, sleepDuration.minute,Color.Black)

                        TextSubPersEspecialColor("Resumen hecho por \nSleepY.Inc", Color.Black)

                    }
                }
            }
        }

    }
}

