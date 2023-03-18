package com.example.foodie.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.foodie.databasedb.database

class Homeviewmodelfactory(
    val mealdata:database
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return homeviewmodel(mealdata) as T
    }
}