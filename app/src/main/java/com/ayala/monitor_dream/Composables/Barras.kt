package com.ayala.monitor_dream.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ayala.monitor_dream.data.DataGrapihc
import com.ayala.monitor_dream.utils.Rcolors
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.renderer.label.SimpleValueDrawer

@Composable

fun BarrasScreen() {

    Column (
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        TextB("Estadísticas")

        Barras()
    }

}

@Composable

fun Barras()
{
    val datos = DataGrapihc.datos

    var barras = ArrayList<BarChartData.Bar>()

    datos.mapIndexed { index, datos ->
        barras.add(
            BarChartData.Bar(
                label = datos.label,
                value = datos.value.toFloat(),
                color = Rcolors().colorRandom())
        )
    }

    BarChart(
        modifier = Modifier.
        padding(20.dp, 40.dp).
        height(200.dp),
        labelDrawer = SimpleValueDrawer(
            drawLocation = SimpleValueDrawer.DrawLocation.XAxis,
        ),
        barChartData = BarChartData(
            bars = barras)
    )

}