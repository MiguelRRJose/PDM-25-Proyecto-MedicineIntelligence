package com.ayala.monitor_dream.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ayala.monitor_dream.R
import com.ayala.monitor_dream.composables.BarGraph
import com.ayala.monitor_dream.composables.ButtonAction
import com.ayala.monitor_dream.composables.CardIcon
import com.ayala.monitor_dream.composables.PersonalBackground
import com.ayala.monitor_dream.composables.PersonalSpacer
import com.ayala.monitor_dream.composables.TextAMPersColr
import com.ayala.monitor_dream.composables.TextEspecialPersColr
import com.ayala.monitor_dream.composables.TextSubDatePersColr
import com.ayala.monitor_dream.composables.TextSubPersColr
import com.ayala.monitor_dream.composables.TextSubPersEspecialColor
import com.ayala.monitor_dream.composables.TextTimePersColr
import com.ayala.monitor_dream.composables.TextTittlePersColr
import com.ayala.monitor_dream.utils.formatTimeAMPM
import com.ayala.monitor_dream.viewModel.DataViewModel
import com.ayala.monitor_dream.viewModel.SleepViewModel

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

