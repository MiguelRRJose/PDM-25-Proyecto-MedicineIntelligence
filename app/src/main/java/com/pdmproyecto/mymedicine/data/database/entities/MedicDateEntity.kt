package com.pdmproyecto.mymedicine.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.pdmproyecto.mymedicine.data.models.MedicDate
import java.util.Date

@Entity(
    tableName = "MedicDate",
    foreignKeys = [
        ForeignKey(
            entity = DoctorEntity::class,
            parentColumns = ["id"],
            childColumns = ["doctorId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PatientEntity::class,
            parentColumns = ["id"],
            childColumns = ["patientId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MedicDateEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val doctorId: Int,
    val patientId: Int,
    val dateTime: Date
)

fun MedicDateEntity.toDomain(): MedicDate{
    return MedicDate(
        id = this.id,
        doctorId = this.doctorId,
        patientId = this.patientId,
        dateTime = this.dateTime
    )
}
