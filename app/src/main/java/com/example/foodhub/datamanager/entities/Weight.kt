package com.example.foodhub.datamanager.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weight_table")
data class Weight(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val uId : String,
    val value : Double,
    val date : Long,
    val pendingSync : Boolean = false
)