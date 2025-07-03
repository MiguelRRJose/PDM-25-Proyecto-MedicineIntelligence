package com.pdmproyecto.mymedicine.utils

import androidx.compose.ui.graphics.Color

class Randcolors
{

    fun colorRandom(): Color {
        var colors = mutableListOf<Color>(

            Color(0XFFF44336),
            Color(0xFFFC8AB0) ,
            Color(0xFFB2BA18) ,
            Color(0xFFFF5722) ,
            Color(0xFF97A5F1) ,
            Color(0XFF03A9F4),
            Color(0XFF009688),
            Color(0XFFCDDC39),
            Color(0XFFFFC107),
            Color(0XFFFF5722),
        )


        val randomNumber = (Math.random() * colors.size).toInt()
        val color = colors[randomNumber]

        colors.removeAt(randomNumber)

        return color

    }

}