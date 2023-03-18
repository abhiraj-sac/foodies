package com.example.foodie.databasedb

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodie.projo.Meal

@Database(entities = [Meal::class], version = 1)
@TypeConverters(typeconvertor::class)
abstract class database: RoomDatabase() {
    abstract fun mealdao():dao

    companion object{
        @Volatile
        var INSTANCE:database? = null
        @Synchronized
        fun getInstance(context: Context):database{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    database::class.java,
                    "meal.db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as database
        }
    }
}