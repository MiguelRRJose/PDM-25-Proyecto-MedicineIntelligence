package com.pdmproyecto.mymedicine.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
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
    entities =
        [
            UserEntity::class,
            PatientEntity::class,
            DoctorEntity::class,
            MedicDateEntity::class,
            MedicineEntity::class
        ],
    version = 1,
    exportSchema = true
)
//Declarando los TypeConverters de Date->Long y Long->Date que Room usará
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    //Definición de Daos
    abstract fun UserDao(): UserDao
    abstract fun PatientDao(): PatientDao
    abstract fun DoctorDao(): DoctorDao
    abstract fun MedicDateDao(): MedicDateDao
    abstract fun MedicineDao(): MedicineDao

    //Companion object para declarar Singleton de database y metodo para inicializarlo
    companion object{
        //volatile obliga a referenciar este recurso desde su dirección de origen en memoria en lugar de referenciarla en cache
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE?: synchronized(this){

                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = AppDatabase::class.java,
                    name = "mymedicine_database"
                )
                    .fallbackToDestructiveMigration(false)
                    .build()
                    .also{
                        INSTANCE = it
                    }

                instance
            }
        }
    }

}