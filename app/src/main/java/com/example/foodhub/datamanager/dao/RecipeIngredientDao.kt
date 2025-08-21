package com.example.foodhub.datamanager.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodhub.datamanager.entities.RecipeIngredient
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeIngredientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeIngredient(ingredient: RecipeIngredient)

    @Query("SELECT * FROM recipe_ingredients WHERE recipeId = :recipeId")
    fun getRecipeIngredients(recipeId: String): Flow<List<RecipeIngredient>>
}