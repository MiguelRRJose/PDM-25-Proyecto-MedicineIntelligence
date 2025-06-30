package com.ayala.monitor_dream.composables

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ayala.monitor_dream.navigation.TimeSleep
import kotlinx.coroutines.delay

// Reutiliza o importa tu data class TimeSleep
// import com.ayala.monitor_dream.navigation.TimeSleep

@Composable
fun TimeSleepCountdownBar(
    modifier: Modifier = Modifier,
    initialTime: TimeSleep,
    onTimeFinished: () -> Unit = {}
) {

    Log.d("CountdownBar", "initialTime H: ${initialTime.hour}, M: ${initialTime.minute}")
    // Convertir el tiempo inicial a segundos totales para facilitar el cálculo del progreso
    val totalSecondsInitial = remember(initialTime) {
        Log.d("CountdownBar", "Recalculando totalSecondsInitial para H: ${initialTime.hour}, M: ${initialTime.minute}")
        (initialTime.hour * 3600) + (initialTime.minute * 60)
    }

    var remainingTime by remember(initialTime) { mutableStateOf(initialTime) }
    var currentTotalSecondsRemaining by remember(initialTime) {
        mutableStateOf(totalSecondsInitial)
    }

    var progress by remember(initialTime) { mutableFloatStateOf(1.0f) }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = "TimeSleepProgressAnimation"
    )

    LaunchedEffect(key1 = initialTime) {
        // Si el contador ya está en cero por el initialTime, no hacer nada.
        if (totalSecondsInitial <= 0) {
            progress = 0f
            // No llamar a onTimeFinished si ya empezamos en cero
            return@LaunchedEffect
        }

        // Reiniciar currentTotalSecondsRemaining al valor de initialTime para este efecto
        currentTotalSecondsRemaining = totalSecondsInitial
        remainingTime = initialTime
        progress = 1.0f

        while (currentTotalSecondsRemaining > 0) {
            delay(1000L) // Retraso de 1 segundo

            // Solo actualizamos si aún hay tiempo
            if (currentTotalSecondsRemaining > 0) { // Doble chequeo por si acaso
                currentTotalSecondsRemaining--

                val newHours = currentTotalSecondsRemaining / 3600
                val newMinutes = (currentTotalSecondsRemaining % 3600) / 60

                remainingTime = TimeSleep(hour = newHours, minute = newMinutes)
                progress = currentTotalSecondsRemaining.toFloat() / totalSecondsInitial.toFloat()
            }
        }

        // El bucle while termina cuando currentTotalSecondsRemaining es 0
        progress = 0f
        onTimeFinished()
    }

    Column(modifier = modifier) {
        LinearProgressIndicator(
            progress = { animatedProgress },
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Objetivo: ${remainingTime.hour}h ${remainingTime.minute}m",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TimeSleepCountdownBarPreview() {
    MaterialTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Contador de 1 hora y 30 minutos:")
            TimeSleepCountdownBar(
                initialTime = TimeSleep(hour = 1, minute = 30),
                onTimeFinished = {
                    // Acción cuando el tiempo termina
                }
            )
            Spacer(Modifier.height(20.dp))
            Text("Contador de 2 minutos:")
            TimeSleepCountdownBar(
                initialTime = TimeSleep(hour = 0, minute = 2),
                onTimeFinished = {
                    // Acción
                }
            )
            Spacer(Modifier.height(20.dp))
            Text("Contador de 0 minutos (ya finalizado):")
            TimeSleepCountdownBar(
                initialTime = TimeSleep(hour = 0, minute = 0)
            )
        }
    }
}