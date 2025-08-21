package com.example.foodhub.datamanager.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_ingredients_table")
data class FavoriteIngredient(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val uid: String,
    val name: String,
    val calories: Double,
    val protein: Double,
    val carbs: Double,
    val fat: Double,
    val fiber : Double


)
