package com.ayala.monitor_dream.Screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import com.ayala.monitor_dream.R
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayala.monitor_dream.ViewModel.SleepViewModel
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale


@Composable
fun SleepScreen(
    alarmTime: String = "06:00 AM",
    onStartSleep: () -> Unit,
    onDetailsClick: () -> Unit
) {
    val currentTime = remember { mutableStateOf(getCurrentFormattedTime()) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            currentTime.value = getCurrentFormattedTime()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A1F33))
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Buenas noches",
                color = Color.White,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = currentTime.value,
                color = Color.White,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.astro_durmiendo),
                contentDescription = "Astronauta dormido",
                modifier = Modifier.size(160.dp)
            )


            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "⏰ $alarmTime",
                color = Color.White,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onStartSleep,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Text("INICIAR SUEÑO")
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = onDetailsClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Text("DETALLES")
            }
        }
    }
}

fun getCurrentFormattedTime(): String {
    val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return sdf.format(Date())
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SleepScreen(
    viewModel: SleepViewModel,
    onDetailsClick: () -> Unit
) {
    val alarmTime = viewModel.alarmTime.collectAsState()//Es probable que las implementaciones de "alarmaTime" no apliquen funciones get y set


    Text(text = "⏰ ${alarmTime.value.format(DateTimeFormatter.ofPattern("hh:mm a"))}")
}


@Preview
@Composable
fun SleepScreenPreview() {
    SleepScreen(
        alarmTime = "06:00 AM",
        onStartSleep = {},
        onDetailsClick = {}
    )
}

