package com.ayala.monitor_dream.utils

import com.ayala.monitor_dream.navigation.TimeSleep

object OperationValuesTime {

    fun CalculateTime(sleepTime : TimeSleep): Int
    {

        if (sleepTime.minute == 0)
        {
            return sleepTime.hour + 59
        }
        else if (sleepTime.hour > 0)
        {
            return sleepTime.hour -1
        }

        return 0
    }

    fun CalculateTime2(sleepTime: TimeSleep): Int
    {
        if (sleepTime.minute == 0)
        {

            return 0
        }
        else if (sleepTime.minute > 0)
        {
            return sleepTime.minute - 1
        }

        return 0
    }
}