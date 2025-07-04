package com.pdmproyecto.mymedicine.notify

import android.Manifest
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.pdmproyecto.mymedicine.MainActivity
import com.pdmproyecto.mymedicine.R  // 👈 Import correcto de tu propio R

class NotificationReceiver : BroadcastReceiver() {
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("Notification!!", "Notificación recibida")

        val contentIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("NAVIGATION_TO_SLEEP_Y_SCREEN", true)
        }

        val pendingIntentFlags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }

        val contentPendingIntent: PendingIntent = PendingIntent.getActivity(
            context,
            1,
            contentIntent,
            pendingIntentFlags
        )

        try {
            val builder = NotificationCompat.Builder(context, "dream_channel_id")
                .setSmallIcon(R.drawable.astro_durmiendo)  // 👈 Ya usando tu package
                .setContentTitle("SleepY te ha mandado una notificación!!!")
                .setContentText("¡Es hora de dormir!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(contentPendingIntent)

            val manager = NotificationManagerCompat.from(context)
            manager.notify(1001, builder.build())
        } catch (e: SecurityException) {
            Log.e("Notification", "Permiso no otorgado: ${e.message}")
        }
    }
}
