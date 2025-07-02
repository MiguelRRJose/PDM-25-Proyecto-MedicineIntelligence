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
import androidx.navigation.NavController
import com.ayala.monitor_dream.R
import com.ayala.monitor_dream.composables.Barras
import com.ayala.monitor_dream.composables.BarrasScreen
import com.ayala.monitor_dream.composables.PersonalBackground
import com.ayala.monitor_dream.composables.TextA
import com.ayala.monitor_dream.composables.TextB
import com.ayala.monitor_dream.composables.TextSub
import com.ayala.monitor_dream.composables.TextTittle
import com.ayala.monitor_dream.composables.TimeCard
import com.ayala.monitor_dream.viewModel.SleepViewModel

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

            TextTittle("Reporte de sueño")

            Spacer(modifier = Modifier.height(16.dp))

            TextSub("Consultando las estadisticas de sueño")

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                navController.popBackStack()
            }) {
                TextSub("Despertar!!!")
            }
        }

        Column(modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(550.dp)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {

                Column () {

                    Row(modifier = Modifier.fillMaxWidth()) {

                        TextB("80")

                        Spacer(modifier = Modifier.width(16.dp))

                        TextA("Estrellitas")
                    }

                    TextA("Puntación de sueño")

                    TextSub("9/9/9")

                    TextA("Alarma: ")

                    TimeCard("Dulces sueños: ", "countdown", R.drawable.alarm_white_1){}

                    BarrasScreen()

                    Row (modifier = Modifier.fillMaxWidth()) {

                        TextA("Duracion del sueño")
                        Spacer(modifier = Modifier.width(16.dp))
                        TextA("Buen día!!!!")

                    }
                }
            }
        }

    }
}

