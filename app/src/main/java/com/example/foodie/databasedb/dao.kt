package com.example.foodie.databasedb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.foodie.projo.Meal

@Dao
interface dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun upsert(meal:Meal)
   @Delete
   suspend fun delete(meal: Meal)

   @Query("SELECT * FROM mealinformation")
    fun getAllmeals():LiveData<List<Meal>>
}