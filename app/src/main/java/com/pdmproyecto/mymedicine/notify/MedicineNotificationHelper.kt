package com.pdmproyecto.mymedicine.notify

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresPermission
import com.pdmproyecto.mymedicine.data.models.Medicine
import com.pdmproyecto.mymedicine.data.models.getIntervalMillis
import java.util.Date


object MedicineNotificationHelper {

    @RequiresPermission(Manifest.permission.SCHEDULE_EXACT_ALARM)
    fun programAlarm(context: Context, medicines: List<Medicine>){
        for (medicine in medicines){

            val nextDose = calculateNextDosis(medicine)
            val finishMillis = medicine.finishDate?.time ?: Long.MAX_VALUE

            if (nextDose <= finishMillis){
                programNotification(context, medicine, nextDose)
            }else{
                Log.d("AlarmHelper", "Medicine  ${medicine.name} outdated")
            }

        }
    }


    //al llegar a la fecha establecida la funcion toma la fecha actual y suma el valor del intervalo (todo con millisegundos)
    private fun calculateNextDosis(medicine: Medicine): Long{
        val interval = medicine.getIntervalMillis()
        val now = System.currentTimeMillis()
        var nextTime = medicine.startDate.time

        while (nextTime < now){
            nextTime += interval
        }

        return nextTime
    }

    @androidx.annotation.RequiresPermission(Manifest.permission.SCHEDULE_EXACT_ALARM)
    fun programNotification(context: Context, medicine: Medicine, triggerAt: Long) {


        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            if (!alarmManager.canScheduleExactAlarms()) {
                val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(intent)

                Log.w("AlarmHelper", "Permiso SCHEDULE_EXACT_ALARM no concedido. Redirigiendo a configuración.")
                return
            }
        }

        val intent = Intent(context, MedicineNotificationReceiver::class.java).apply {
            putExtra("medicine", medicine)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            medicine.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )


        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            triggerAt,
            pendingIntent
        )

        Log.d("AlarmHelper", "Notification set for ${medicine.name} at ${Date(triggerAt)}")
    }
}