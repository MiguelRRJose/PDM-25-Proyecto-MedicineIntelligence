package com.ayala.monitor_dream.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ayala.monitor_dream.R
import com.ayala.monitor_dream.composables.ButtonSleepScreen
import com.ayala.monitor_dream.composables.PersonalBackground
import com.ayala.monitor_dream.composables.SelectedImage
import com.ayala.monitor_dream.composables.TextSub
import com.ayala.monitor_dream.composables.TextSummary
import com.ayala.monitor_dream.composables.TextTittle
import com.ayala.monitor_dream.utils.formatTimeAMPM
import com.ayala.monitor_dream.viewModel.SleepViewModel

@Composable
fun SleepDetail(
    navController: NavController,
    viewModel: SleepViewModel,
) {

    val alarmTime by viewModel.alarmTime.collectAsState()
    val storedSleepTime by viewModel.startTime.collectAsState()
    val sleepDuration by viewModel.sleepTimeDurationH.collectAsState()
    val dateDetails by viewModel.dateDetails.collectAsState()


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        PersonalBackground(R.drawable.backgroud_1 , "background")

        Column(
            modifier = Modifier.align(Alignment.Center) ,
            horizontalAlignment = Alignment.CenterHorizontally ,
            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {
            TextTittle("Detalles del\nsueño actual")

            Spacer(modifier = Modifier.height(8.dp))

            SelectedImage(R.drawable.astro_durmiendo , "Astronauta dormido")

            Spacer(modifier = Modifier.height(8.dp))

            TextSummary(
                dateDetails.dayOfMonth,
                dateDetails.month,
                dateDetails.year,
                sleepDuration.hour,
                sleepDuration.minute,
                formatTimeAMPM(storedSleepTime.hour,storedSleepTime.minute),
                formatTimeAMPM(alarmTime.hour,alarmTime.minute)
            )

            Spacer(modifier = Modifier.height(16.dp))

            ButtonSleepScreen("Volver") { navController.popBackStack() }

            Spacer(modifier = Modifier.height(16.dp))


            TextSub("SleepY aprueba tu horario :D")

        }

    }
}
