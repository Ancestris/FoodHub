package com.example.foodhub.datamanager.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodhub.datamanager.entities.Person

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun savePerson(person: Person)
    @Query("SELECT * FROM PERSON_TABLE WHERE uid = :uId LIMIT 1")
    suspend fun  getPerson( uId : String) : Person?
}