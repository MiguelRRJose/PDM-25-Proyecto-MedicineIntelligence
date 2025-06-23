package com.pdmproyecto.mymedicine.data.converters

import androidx.room.TypeConverter

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
    fun TimestampToDate(value: Long?): java.util.Date? {
        return value?.let { java.util.Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: java.util.Date?): Long? {
        return date?.time
    }
}