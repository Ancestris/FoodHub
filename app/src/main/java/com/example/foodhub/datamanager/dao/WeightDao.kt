package com.example.foodhub.datamanager.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodhub.datamanager.entities.Weight

@Dao
interface WeightDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWight(weight : Weight)

    @Query("SELECT * FROM weight_table WHERE uId = :uId  ORDER BY date DESC")
    fun getWeights(uId: String) :  kotlinx.coroutines.flow.Flow<List<Weight>>

    @Query("SELECT * FROM weight_table WHERE pendingSync = 1")
    suspend fun getPendingWeights() : List<Weight>?
}