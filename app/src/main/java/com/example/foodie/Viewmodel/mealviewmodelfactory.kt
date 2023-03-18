package com.example.foodie.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.foodie.databasedb.database

class mealviewmodelfactory(
    val mealdata:database
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MealViewModel(mealdata) as T
    }
}