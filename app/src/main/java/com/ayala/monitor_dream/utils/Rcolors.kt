package com.ayala.monitor_dream.utils

import androidx.compose.ui.graphics.Color

class Rcolors
{

    fun colorRandom(): Color {
        var colors = mutableListOf<Color>(

            Color(0XFFF44336),
            Color(0XFFE91E63),
            Color(0XFF9C27B0),
            Color(0XFF673AB7),
            Color(0XFF3F51B5),
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