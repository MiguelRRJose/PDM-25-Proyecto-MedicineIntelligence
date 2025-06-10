package com.pdmproyecto.mymedicine.data

data class Medicine(
    val id: Int,
    val name: String,
    val unit: String,
    val amount: Float,
    val startDate: String,
    val lastDate: String,
    val timeLap: String
)


val dummyMedicines = listOf(
    Medicine(
        id = 1,
        name = "Paracetamol",
        unit = "pill",
        amount = 1f,
        timeLap = "Cada 8 horas",
        startDate = "2025-06-01",
        lastDate = "2025-06-07"
    ),
    Medicine(
        id = 2,
        name = "Ibuprofeno",
        unit = "pill",
        amount = 1f,
        timeLap = "Cada 6 horas",
        startDate = "2025-06-03",
        lastDate = "2025-06-10"
    ),
    Medicine(
        id = 3,
        name = "Amoxicilina",
        unit = "pill",
        amount = 1f,
        timeLap = "Cada 12 horas",
        startDate = "2025-06-02",
        lastDate = "2025-06-08"
    ),
    Medicine(
        id = 4,
        name = "Omeprazol",
        unit = "pill",
        amount = 1f,
        timeLap = "Antes del desayuno",
        startDate = "2025-06-05",
        lastDate = "2025-06-20"
    )
)
