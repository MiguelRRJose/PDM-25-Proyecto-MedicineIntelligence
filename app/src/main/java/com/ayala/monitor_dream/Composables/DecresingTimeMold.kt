package com.ayala.monitor_dream.composables

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ayala.monitor_dream.R
import com.ayala.monitor_dream.navigation.TimeSleep
import kotlinx.coroutines.delay

@Composable
fun TimeSleepCountdownBar(
    modifier: Modifier = Modifier,
    initialTime: TimeSleep,
    onTimeFinished: () -> Unit = {}
) {

    Log.d("CountdownBar", "initialTime H: ${initialTime.hour}, M: ${initialTime.minute}")


    val totalSecondsInitial = remember(initialTime) {

        Log.d("CountdownBar", "Recalculando totalSecondsInitial para H: ${initialTime.hour}, M: ${initialTime.minute}")

        (initialTime.hour * 3600) + (initialTime.minute * 60)
    }

    var remainingTime by remember(initialTime) { mutableStateOf(initialTime) }
    var currentTotalSecondsRemaining by remember(initialTime) {
        mutableIntStateOf(totalSecondsInitial)
    }

    var progress by remember(initialTime) { mutableFloatStateOf(1.0f) }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = "TimeSleepProgressAnimation"
    )

    LaunchedEffect(key1 = initialTime) {

        if (totalSecondsInitial <= 0) {
            progress = 0f

            return@LaunchedEffect
        }

        currentTotalSecondsRemaining = totalSecondsInitial
        remainingTime = initialTime
        progress = 1.0f

        while (currentTotalSecondsRemaining > 0) {
            delay(1000L)

            if (currentTotalSecondsRemaining > 0) {
                currentTotalSecondsRemaining--

                val newHours = currentTotalSecondsRemaining / 3600
                val newMinutes = (currentTotalSecondsRemaining % 3600) / 60

                remainingTime = TimeSleep(hour = newHours, minute = newMinutes)
                progress = currentTotalSecondsRemaining.toFloat() / totalSecondsInitial.toFloat()
            }
        }

        progress = 0f
        onTimeFinished()
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        LinearProgressIndicator(
            progress = { animatedProgress },
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
        Spacer(modifier = Modifier.height(8.dp))

        TimeCard(" ", "${remainingTime.hour} h: ${remainingTime.minute} min", R.drawable.alarm_white_1){}
    }
}
