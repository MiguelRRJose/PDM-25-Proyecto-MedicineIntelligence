package com.pdmproyecto.mymedicine.Composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.nativeCanvas


@Composable
fun BarChart(
    barChartData: BarChartData,
    modifier: Modifier = Modifier
) {
    val bars = barChartData.bars
    val maxValue = bars.maxOfOrNull { it.value } ?: 1f

    Canvas(modifier = modifier.fillMaxWidth()) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val barWidth = canvasWidth / (bars.size * 2f)

        bars.forEachIndexed { index, bar ->
            val barHeight = (bar.value / maxValue) * canvasHeight * 0.8f
            val x = index * (barWidth * 2) + barWidth / 2
            val y = canvasHeight - barHeight

            drawRect(
                color = bar.color,
                topLeft = androidx.compose.ui.geometry.Offset(x, y),
                size = Size(barWidth, barHeight)
            )

            drawIntoCanvas {
                it.nativeCanvas.drawText(
                    bar.label,
                    x,
                    canvasHeight,
                    android.graphics.Paint().apply {
                        textSize = 20f
                        color = android.graphics.Color.BLACK
                        textAlign = android.graphics.Paint.Align.CENTER
                        isAntiAlias = true
                        isFakeBoldText = true
                    }
                )
            }
        }
    }
}
