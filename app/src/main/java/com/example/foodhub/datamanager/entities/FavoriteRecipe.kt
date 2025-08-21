package com.example.foodhub.datamanager.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_recipes_table")
data class FavoriteRecipe(
    @PrimaryKey val recipeId: String,
    val own_uId : String,
    val uid: String,
    val name: String,
    val calories: Double,
    val protein: Double,
    val carbs: Double,
    val fat: Double,
    val fiber : Double
)
