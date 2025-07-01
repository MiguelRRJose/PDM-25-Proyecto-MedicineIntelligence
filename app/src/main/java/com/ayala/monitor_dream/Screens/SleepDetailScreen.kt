package com.ayala.monitor_dream.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ayala.monitor_dream.R
import com.ayala.monitor_dream.composables.ButtonAction
import com.ayala.monitor_dream.composables.ButtonSleepScreen
import com.ayala.monitor_dream.composables.PersonalBackground
import com.ayala.monitor_dream.composables.SelectedImage
import com.ayala.monitor_dream.viewModel.SleepViewModel

@Composable
fun SleepDetail(
    navController: NavController,
    viewModel: SleepViewModel,
) {
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
            Text(
                text = "Detalles del \n" +
                        "sueño actual" ,
                color = Color.White ,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            SelectedImage(R.drawable.astro_durmiendo , "Astronauta dormido")

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Fecha:\n"+
                        "Hora actual:\n"+
                        "Alarma Seleccionada\n" +
                        "Duarción del sueño" ,
                color = Color.White ,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            ButtonSleepScreen("Volver") { navController.popBackStack() }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "SleepY aprueba tu horario :D")

        }

    }
}
