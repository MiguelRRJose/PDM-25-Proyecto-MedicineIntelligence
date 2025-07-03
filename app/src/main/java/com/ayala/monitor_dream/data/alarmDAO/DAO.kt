package com.ayala.monitor_dream.data.alarmDAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ayala.monitor_dream.data.UserSleepData
import kotlinx.coroutines.flow.Flow

@Dao
interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(userSleepData: UserSleepData)

    @Query("DELETE FROM data_graph")
    suspend fun deleteAllData()

    @Query("SELECT * FROM data_graph ORDER BY id ASC")
    fun getAllData(): Flow<List<UserSleepData>>

    @Query("SELECT COUNT(*) FROM data_graph")
    fun getTotalItemCount(): Flow<Int>

}