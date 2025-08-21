package com.example.foodhub.datamanager

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodhub.datamanager.dao.FavoriteIngredientDao
import com.example.foodhub.datamanager.dao.FavoriteRecipeDao
import com.example.foodhub.datamanager.dao.PersonDao
import com.example.foodhub.datamanager.dao.RecipeIngredientDao
import com.example.foodhub.datamanager.dao.WeightDao
import com.example.foodhub.datamanager.entities.FavoriteIngredient
import com.example.foodhub.datamanager.entities.FavoriteRecipe
import com.example.foodhub.datamanager.entities.Person
import com.example.foodhub.datamanager.entities.RecipeIngredient
import com.example.foodhub.datamanager.entities.Weight

@Database(
    version = 1,
    entities =[
        Person::class,
        Weight::class,
        FavoriteIngredient::class,
        FavoriteRecipe::class,
        RecipeIngredient::class
    ]
)

abstract class AppDatabase : RoomDatabase(){
    abstract fun personDao() : PersonDao
    abstract fun weightDao() : WeightDao
    abstract fun favoriteRecipeDao() : FavoriteRecipeDao
    abstract fun favoriteIngredientDao() : FavoriteIngredientDao
    abstract fun recipeIngredientDao() : RecipeIngredientDao


    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDataBase(context : Context) : AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext ,
                    AppDatabase::class.java,
                    "UsersDatabase")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}