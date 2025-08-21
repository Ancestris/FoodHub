package com.example.foodhub.datamanager.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodhub.datamanager.entities.FavoriteIngredient
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteIngredientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteIngredient(ingredient: FavoriteIngredient)

    @Query("SELECT * FROM fav_ingredients_table WHERE uId = :uid")
    fun getFavoriteIngredients(uid: String): Flow<List<FavoriteIngredient>>
}