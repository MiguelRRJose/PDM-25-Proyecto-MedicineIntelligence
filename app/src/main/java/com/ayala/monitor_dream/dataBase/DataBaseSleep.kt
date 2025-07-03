package com.ayala.monitor_dream.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ayala.monitor_dream.data.UserSleepData
import com.ayala.monitor_dream.data.alarmDAO.DataDao

@Database(entities = [UserSleepData::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dataDao(): DataDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "monitor_dream_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}