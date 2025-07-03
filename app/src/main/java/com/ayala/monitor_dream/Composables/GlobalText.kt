package com.ayala.monitor_dream.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.sp

@Composable
fun TextTittlePersColr(text: String, color: Color)
{
    Text(text,
        color = color,
        fontSize = 40.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = Bold

    )
}

@Composable
fun TextSubDatePersColr(nameDay: String,days: Int,month: Int, year: Int, color: Color)
{
    TextSubPersEspecialColor("Fecha para la sesión actual: ${days}/${month}/${year}\n" +
            "\nHoy es  $nameDay"+" :D ",color)
}

@Composable
fun TextTimePersColr(hour: Int, minute: Int, color: Color)
{

    TextSubPersEspecialColor("Duración del \nsueño actual: $hour h: $minute min", color)
}

@Composable

fun TextSubPersColr(text: String, color: Color)
{
    Text(text, color = color, fontSize = 16.sp)
}

@Composable
fun TextSubPersEspecialColor(text: String, color: Color)
{
    Text(text, color = color,
        fontSize = 16.sp,
        fontWeight = Bold,
        fontFamily = FontFamily.Serif
    )
}


@Composable
fun TextEspecialPersColr(text: String, color: Color)
{
    Text(text, color = color, fontSize = 20.sp, fontWeight = Bold)
}


@Composable
fun TextAMPersColr(text: String, color: Color)
{
    TextSubPersEspecialColor("Alarma actual: $text",color)
}

@Composable
fun TextSummary(
    nameDay: String ,
    days: Int ,
    month: Int ,
    year: Int ,
    hour: Int ,
    minute: Int ,
    actualHour: String ,
    alarmHour: String)
{

    Column {

        TextSubDatePersColr(nameDay , days , month , year , Color.White)
        PersonalSpacer(16)
        TextSubPersEspecialColor("Hora seleccionada sueño: $actualHour" , Color.White)
        PersonalSpacer(16)
        TextAMPersColr(alarmHour , Color.White)
        PersonalSpacer(16)
        TextTimePersColr(hour , minute , Color.White)

    }
}
