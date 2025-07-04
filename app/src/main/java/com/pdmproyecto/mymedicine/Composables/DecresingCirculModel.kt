package com.pdmproyecto.mymedicine.Composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pdmproyecto.mymedicine.R
import com.pdmproyecto.mymedicine.ui.screens.Navigation.TimeSleep
import kotlinx.coroutines.delay

@Composable
fun CircularTimeSleepCountdown(
    modifier: Modifier = Modifier,
    initialTime: TimeSleep,
    onTimeFinished: () -> Unit = {},
    indicatorSize: Dp = 100.dp,
    strokeWidth: Dp = 8.dp,
    trackColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    progressColor: Color = MaterialTheme.colorScheme.primary
) {
    val totalSecondsInitial = remember(initialTime) { initialTime.toTotalSeconds() }

    var currentTotalSecondsRemaining by remember(initialTime) {
        mutableIntStateOf(totalSecondsInitial)
    }

    var progress by remember(initialTime) {
        mutableFloatStateOf(if (totalSecondsInitial > 0) 1.0f else 0.0f)
    }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = "CircularTimeSleepAnimation"
    )

    LaunchedEffect(key1 = initialTime) {
        currentTotalSecondsRemaining = totalSecondsInitial
        progress = if (totalSecondsInitial > 0) 1.0f else 0.0f


        if (totalSecondsInitial <= 0) {
            return@LaunchedEffect
        }

        while (currentTotalSecondsRemaining > 0) {
            delay(1000L)

            currentTotalSecondsRemaining--

            progress = if (totalSecondsInitial > 0) {
                currentTotalSecondsRemaining.toFloat() / totalSecondsInitial.toFloat()
            } else {
                0f
            }
        }

        onTimeFinished()
    }

    Box(
        modifier = modifier.size(indicatorSize),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = { 1f },
            modifier = Modifier.matchParentSize(),
            color = trackColor,
            strokeWidth = strokeWidth,
            strokeCap = StrokeCap.Round
        )
        CircularProgressIndicator(
            progress = { animatedProgress },
            modifier = Modifier.matchParentSize(),
            color = progressColor,
            strokeWidth = strokeWidth,
            strokeCap = StrokeCap.Round
        )

        SelectedImage(R.drawable.astro_durmiendo , "Astronauta dormido")
    }
}
