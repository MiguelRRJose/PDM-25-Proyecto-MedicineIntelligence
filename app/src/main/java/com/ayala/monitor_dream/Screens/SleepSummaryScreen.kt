package com.ayala.monitor_dream.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ayala.monitor_dream.R
import com.ayala.monitor_dream.composables.PersonalBackground
import com.ayala.monitor_dream.composables.TimeCard
import com.ayala.monitor_dream.viewModel.SleepViewModel
import kotlinx.coroutines.delay
import com.ayala.monitor_dream.utils.parseTimeToCalendar
import com.ayala.monitor_dream.utils.parseTimeToCalendar2

@Composable
fun SleepTrackingScreen(
    navController: NavController,
    viewModel: SleepViewModel,
) {
    //Dato ViewModel
    val alarmTime = viewModel.alarmTime.collectAsState().value

    val startSleepTime = viewModel.startTime.collectAsState().value

    val currenTimeMillis = remember {mutableStateOf(System.currentTimeMillis())}


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
                .padding(top = 180.dp,end = 16.dp),
            horizontalAlignment = Alignment.End

        ) {
            Text(
                text = "Reporte de sueño",
                color = Color.White,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Consultando las estadisticas de sueño",
                color = Color.White,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                navController.popBackStack()
            }) {
                Text("DESPERTAR")
            }
        }

        Column(modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {

                Column () {

                    Row(modifier = Modifier.fillMaxWidth()) {

                        Text(text = "80")

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(text = "ESTRELLITAS?")
                    }

                    Text(text = "Puntación de sueño")
                    Text ("30/06/2025")

                    TimeCard("Dulces sueños: ", "countdown", R.drawable.alarm_white_1){}

                    //De alguna forma la grafica

                    Row () {

                        Text(text = "Duracion del sueño")
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = "Buen dia!!!!")

                    }
                }
            }
        }

    }
}

