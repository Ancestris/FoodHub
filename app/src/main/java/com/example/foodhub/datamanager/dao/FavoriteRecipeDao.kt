package com.example.foodhub.datamanager.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodhub.datamanager.entities.FavoriteRecipe
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteRecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(recipe: FavoriteRecipe)

    @Query("SELECT * FROM fav_recipes_table WHERE uid = :uid")
    fun getFavoriteRecipes(uid: String): Flow<List<FavoriteRecipe>>
}