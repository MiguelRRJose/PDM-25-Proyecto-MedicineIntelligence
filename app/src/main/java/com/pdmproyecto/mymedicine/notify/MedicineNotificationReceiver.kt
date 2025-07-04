package com.pdmproyecto.mymedicine.notify

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresPermission
import com.pdmproyecto.mymedicine.R
import com.pdmproyecto.mymedicine.data.models.Medicine
import com.pdmproyecto.mymedicine.data.models.getIntervalMillis
import java.util.Date

class MedicineNotificationReceiver: BroadcastReceiver() {

    @RequiresPermission(Manifest.permission.SCHEDULE_EXACT_ALARM)
    override fun onReceive(context: Context, intent: Intent?) {
        val medicine = intent?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getSerializableExtra("medicine", Medicine::class.java)
            } else {
                @Suppress("DEPRECATION")
                it.getSerializableExtra("medicine") as? Medicine
            }
        } ?: return

        Log.d("MedicineAlarmReceiver", "Alarma recibida para: ${medicine.name}")

        // Mostrar notificación
        mostrarNotificacion(context, medicine)

        // Programar siguiente alarma
        val nextTime = Date(System.currentTimeMillis() + medicine.getIntervalMillis())
        val nextMillis = nextTime.time

        if (medicine.finishDate == null || nextTime.before(medicine.finishDate)) {
            val updatedMedicine = medicine.copy(startDate = nextTime)
            MedicineNotificationHelper.programNotification(context, updatedMedicine, nextMillis)
        } else {
            Log.d("MedicineAlarmReceiver", "Fin del ciclo para ${medicine.name}")
        }
    }

    @Suppress("DEPRECATION")
    private fun mostrarNotificacion(context: Context, medicine: Medicine) {
        val builder = Notification.Builder(context)
            .setSmallIcon(R.drawable.astro_durmiendo)
            .setContentTitle("¡Hora de tomar ${medicine.name}!")
            .setContentText("Dosis: ${medicine.amount} ${medicine.unit}")
            .setAutoCancel(true)
            .setWhen(System.currentTimeMillis())
            .setShowWhen(true)
            .setPriority(Notification.PRIORITY_HIGH)

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(medicine.id, builder.build())
    }
}