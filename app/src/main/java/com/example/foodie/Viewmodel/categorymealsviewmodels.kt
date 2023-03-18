package com.example.foodie.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.projo.Categorymeals
import com.example.foodie.projo.categorylist
import com.example.foodie.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class categorymealsviewmodels: ViewModel() {
    val mutablelivedata = MutableLiveData<List<Categorymeals>>()
    fun getmealsbycategory(Categoryname:String){
        RetrofitInstance.api.getmealsbycategory(Categoryname).enqueue(object : Callback<categorylist>{
            override fun onResponse(call: Call<categorylist>, response: Response<categorylist>) {
                response.body()?.let { mealslist ->
             mutablelivedata.postValue(mealslist.meals)
                }
            }

            override fun onFailure(call: Call<categorylist>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
    fun observemealsLivedata(): LiveData<List<Categorymeals>>{
        return mutablelivedata
    }
}