package com.ayala.monitor_dream.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp


@Composable

fun TextTittle(text: String)
{
    Text(text, color = Color.White, fontSize = 30.sp)
}

@Composable

fun TextSubDate(days: Int,month: Int, year: Int)
{
    Text(
        "Fecha actual: ${days}/${month}/${year}",
        color = Color.White, fontSize = 16.sp)
}
@Composable
fun TextTime(hour: Int, minute: Int)
{
    Text(
        "Duración del sueño: ${hour} h: ${minute} min",
        color = Color.White, fontSize = 16.sp
    )
}

@Composable

fun TextSub(text: String)
{
    Text( text, color = Color.White, fontSize = 16.sp)
}

@Composable
fun TextSummary(
    days: Int ,
    month: Int ,
    year: Int ,
    hour: Int ,
    minute: Int ,
    actualHour: String ,
    alarmHour: String)
{
    TextSubDate(days,month,year)
    TextSub("Hora sueño: ${actualHour}")
    TextSub("Alarma Seleccionada: ${alarmHour}")
    TextTime(hour,minute)
}
