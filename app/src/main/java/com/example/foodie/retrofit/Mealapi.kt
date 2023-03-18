package com.example.foodie.retrofit

import com.example.foodie.projo.Meal
import com.example.foodie.projo.MealList
import com.example.foodie.projo.catList
import com.example.foodie.projo.categorylist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface Mealapi {
    @GET("random.php")
    fun getrandommeal():Call<MealList>
    @GET("lookup.php?")
    fun getMealdetails(@Query("i") id:String): Call<MealList>
    @GET("filter.php?")
    fun getpopularitems(@Query("c") categoryname:String): Call<categorylist>

    @GET("categories.php")
    fun getcatogories() : Call<catList>
    @GET("filter.php")
    fun getmealsbycategory(@Query("c") CategoryName: String): Call<categorylist>
}
