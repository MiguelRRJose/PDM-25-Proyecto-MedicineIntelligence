package com.pdmproyecto.mymedicine.data.database.converters

import androidx.room.TypeConverter
import java.util.Date

/*
Room no reconoce nativamente java.util.Date
Converters es una clase que servirá como adaptador para estos datos
contiene funciones que reciben un java.util.Date y lo convierte en un Long y
viceversa.
De esta forma al entrar los datos estaran en un formato que Room si reconoce
y al salir se transforman devuelta en el tipo de datos que la app necesita
 */
class Converters {
    @TypeConverter
    fun TimestampToDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}