package com.ayala.monitor_dream.notify

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?)
    {
        Log.d("Notification!!", "Notificación recibida")

        try {
            val builder = NotificationCompat.Builder(context, "dream_channel_id")
                .setSmallIcon(com.ayala.monitor_dream.R.drawable.moon_purple_2)
                .setContentTitle("SleepY te ha mandado una notificación!!!")
                .setContentText("¡Es hora de dormir!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            val manager = NotificationManagerCompat.from(context)
            manager.notify(1001, builder.build())
            Log.d("Notification", "Notificación enviada")
        } catch (e: SecurityException) {
            Log.e("Notification", "Permiso no otorgado: ${e.message}")
        }
    }
}

