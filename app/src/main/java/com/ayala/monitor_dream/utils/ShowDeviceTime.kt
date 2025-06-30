package com.ayala.monitor_dream.utils

object ShowDeviceTime
{

fun showDeviceTime()
{
    val startTimeMillis: Long = System.currentTimeMillis()
    val currentTime = convertMillisToActualData(startTimeMillis)
    //val formattedTime = formatTimeAMPM2(currentTime)
}

}
