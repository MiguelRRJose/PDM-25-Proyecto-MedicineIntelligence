import com.ayala.monitor_dream.model.ActualTime
import com.ayala.monitor_dream.model.AlarmData
import java.util.Calendar

fun parseTimeToCalendar(alarmData: AlarmData): Calendar {
    val wakeUpCalendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, alarmData.hour)
        set(Calendar.MINUTE, alarmData.minute)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    val now = Calendar.getInstance()

    // Si la hora ya pasó, se programa para el siguiente día
    if (wakeUpCalendar.before(now)) {
        wakeUpCalendar.add(Calendar.DAY_OF_MONTH, 1)
    }

    return wakeUpCalendar
}

fun parseTimeToCalendar2(actualTime: ActualTime): Calendar {
   return Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, actualTime.hour)
        set(Calendar.MINUTE, actualTime.minute)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }


}
