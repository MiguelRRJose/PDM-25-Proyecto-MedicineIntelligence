package com.ayala.monitor_dream.notify

import android.Manifest
import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresPermission
import java.util.Calendar

object NotificationHelper {
    private const val CHANNEL_ID = "dream_channel_id"

    @SuppressLint("ScheduleExactAlarm")
    @RequiresPermission(Manifest.permission.SCHEDULE_EXACT_ALARM)

    fun scheduleNotificationAtTime(
        context: Context ,
        minuteSelected: Int,
        sleepHour:Int,
        sleepMinute:Int) {

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, sleepHour)
            set(Calendar.MINUTE, sleepMinute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)

            add(Calendar.MINUTE,-minuteSelected)

            // Si la hora ya pasó hoy, programa para mañana
            if (before(Calendar.getInstance())) {
                add(Calendar.DAY_OF_MONTH, 1)

                Log.d("Notificacion!!", "dentro de mañana")
            }else
            {
                Log.d("Notificacion!!", "dentro de hoy")
            }
        }

        val intent = Intent(context, NotificationReceiver::class.java)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
       alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
          pendingIntent
       )

        Log.d("Notificacion", "dentro de ${calendar.time}")

        }

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Sueño"
            val descriptionText = "Canal para alarmas del monitor"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            Log.d("NotificationHelper", "Notification channel created")
        }
    }

}




