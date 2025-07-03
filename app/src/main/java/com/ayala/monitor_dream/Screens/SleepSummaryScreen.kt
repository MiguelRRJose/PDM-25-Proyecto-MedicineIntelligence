package com.ayala.monitor_dream.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import com.ayala.monitor_dream.composables.TextA
import com.ayala.monitor_dream.composables.TextAM
import com.ayala.monitor_dream.composables.TextB
import com.ayala.monitor_dream.composables.TextSub
import com.ayala.monitor_dream.composables.TextSubDate
import com.ayala.monitor_dream.composables.TextSubDateM
import com.ayala.monitor_dream.composables.TextTime
import com.ayala.monitor_dream.composables.TextTimeM
import com.ayala.monitor_dream.composables.TextTittle
import com.ayala.monitor_dream.composables.TimeCard
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

    val startSleepTime = viewModel.startTime.collectAsState().value

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

            TextTittle("Reporte de sueño")

            Spacer(modifier = Modifier.height(16.dp))

            TextSub("Consultando las estadisticas de sueño")

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                ButtonAction("Volver")
                {navController.popBackStack()}

                Spacer(modifier = Modifier.width(16.dp))

                ButtonAction("Borrar datos")
                {dataViewModel.clearAllData()}

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

                Column () {

                    Row(modifier = Modifier.fillMaxWidth()) {

                        TextA("Cantidad de sesiones")

                        Spacer(modifier = Modifier.width(16.dp))

                        TextB(totalCount.toString())
                    }


                    Spacer(modifier = Modifier.height(16.dp))

                    TextSubDateM(dateDetails.nameDay,dateDetails.dayOfMonth, dateDetails.month, dateDetails.year)

                    Spacer(modifier = Modifier.height(16.dp))

                    TextAM(formatTimeAMPM(alarmTime.hour, alarmTime.minute))

                    CardIcon(R.drawable.moon_purple_2,"Dulces sueños")

                    BarGraph()

                    Spacer(modifier = Modifier.height(30.dp))

                    Row (modifier = Modifier.fillMaxWidth()) {

                        TextTimeM(sleepDuration.hour, sleepDuration.minute)
                        Spacer(modifier = Modifier.width(16.dp))
                        TextA("Feliz descanso!!!!")

                    }
                }
            }
        }

    }
}

