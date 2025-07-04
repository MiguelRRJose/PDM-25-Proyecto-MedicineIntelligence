package com.pdmproyecto.mymedicine.data.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pdmproyecto.mymedicine.data.UserSleepData
import com.pdmproyecto.mymedicine.data.alarmDAO.DataDao
import com.pdmproyecto.mymedicine.data.database.converters.Converters
import com.pdmproyecto.mymedicine.data.database.daos.DoctorDao
import com.pdmproyecto.mymedicine.data.database.daos.MedicDateDao
import com.pdmproyecto.mymedicine.data.database.daos.MedicineDao
import com.pdmproyecto.mymedicine.data.database.daos.PatientDao
import com.pdmproyecto.mymedicine.data.database.daos.UserDao
import com.pdmproyecto.mymedicine.data.database.entities.DoctorEntity
import com.pdmproyecto.mymedicine.data.database.entities.MedicDateEntity
import com.pdmproyecto.mymedicine.data.database.entities.MedicineEntity
import com.pdmproyecto.mymedicine.data.database.entities.PatientEntity
import com.pdmproyecto.mymedicine.data.database.entities.UserEntity

@Database(
    entities = [
        UserEntity::class,
        PatientEntity::class,
        DoctorEntity::class,
        MedicDateEntity::class,
        MedicineEntity::class,
        UserSleepData::class // ✅ Agregado desde DatabaseSleep
    ],
    version = 2, // ✅ Aumentado para reflejar cambios estructurales
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    // DAOs existentes
    abstract fun UserDao(): UserDao
    abstract fun PatientDao(): PatientDao
    abstract fun DoctorDao(): DoctorDao
    abstract fun MedicDateDao(): MedicDateDao
    abstract fun MedicineDao(): MedicineDao

    // ✅ DAO desde DatabaseSleep
    abstract fun dataDao(): DataDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mymedicine_database" // ✅ Nombre de base unificado
                )
                    .fallbackToDestructiveMigration() // ⚠️ Destruye datos en cambios de versión (ok en dev)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}