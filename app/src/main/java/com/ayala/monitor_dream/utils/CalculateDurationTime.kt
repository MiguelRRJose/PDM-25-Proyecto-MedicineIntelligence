package com.ayala.monitor_dream.utils

object CalculateDurationTime
{
    fun calculateHour(sleepHour: Int, sleepMinute: Int, alarmHour: Int, alarmMinute: Int): Int {

        val sleepTotalMinutes = sleepHour * 60 + sleepMinute
        val alarmTotalMinutes = alarmHour * 60 + alarmMinute

        val duration = if (alarmTotalMinutes - sleepTotalMinutes < 0)
            (alarmTotalMinutes - sleepTotalMinutes + 24 * 60)
        else
            alarmTotalMinutes - sleepTotalMinutes

        return duration / 60
    }

    fun calculateMinute(sleepMinute: Int, alarmMinute: Int): Int {

        val sleepTotalMinutes = sleepMinute
        val alarmTotalMinutes = alarmMinute

        return if (alarmTotalMinutes - sleepTotalMinutes < 0)
        {
            (alarmTotalMinutes - sleepTotalMinutes + 60)
        }
        else
        {
            alarmTotalMinutes - sleepTotalMinutes
        }
    }

}