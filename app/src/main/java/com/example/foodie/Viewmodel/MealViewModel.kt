package com.example.foodie.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.databasedb.database
import com.example.foodie.projo.Meal
import com.example.foodie.projo.MealList
import com.example.foodie.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel(
  val  mealdatabase:database
):ViewModel() {
    private  var MealLivedata=MutableLiveData<Meal>()
    fun getmealdeatil(id:String){
     RetrofitInstance.api.getMealdetails(id).enqueue(object : Callback<MealList>{
         override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
             if(response.body() != null){
                 MealLivedata.value = response.body()!!.meals[0]
             }
             else{
                 return
             }
         }

         override fun onFailure(call: Call<MealList>, t: Throwable) {

         }

     })
    }
    fun ObserveMeallivedata():LiveData<Meal>{
        return MealLivedata
    }
    fun insert(meal:Meal){
        viewModelScope.launch {
           mealdatabase.mealdao().upsert(meal)
        }
    }
    fun delete(meal:Meal){
        viewModelScope.launch {
            mealdatabase.mealdao().delete(meal)
        }
    }
}