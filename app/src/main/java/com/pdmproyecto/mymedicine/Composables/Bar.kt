package com.pdmproyecto.mymedicine.Composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdmproyecto.mymedicine.ViewModel.DataViewModel
import com.pdmproyecto.mymedicine.data.UserSleepData
import com.pdmproyecto.mymedicine.utils.Randcolors
import com.pdmproyecto.mymedicine.utils.VerticalLabelDrawer


@Composable

fun BarGraph(
    dataViewModel: DataViewModel = viewModel()) {

    val dataFromDb by dataViewModel.allDataItems.collectAsState(initial = emptyList())

    Column (
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        if (dataFromDb.isEmpty()) {

            TextTittlePersColr("\nNO EXISTE INFORMACIÓN!!!!", Color.Black)
        }
        else{
            Bars(dataPoints = dataFromDb,
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 20.dp))
        }
    }

}

@Composable
fun Bars(
    dataPoints: List<UserSleepData> ,
    modifier: Modifier = Modifier
) {

    val rColors = Randcolors()

    val barras = dataPoints.map { dataFromRoom ->

        val personalLabel = "Dia: ${dataFromRoom.label}, Sesion: ${dataFromRoom.id}"

        BarChartData.Bar(
            label = personalLabel,
            value = dataFromRoom.value.toFloat(),
            color = rColors.colorRandom()
        )
    }
    //Como segunda confirmación
    if (barras.isEmpty()) {
        TextTittlePersColr("NO HAY BARRAS PARA MOSTRAR", Color.Black)
        return
    }

    BarChart(
        barChartData = BarChartData(barras),
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp) // o lo que quieras
    )


}