package com.example.foodie.Viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.foodie.databasedb.database
import com.example.foodie.projo.*
import com.example.foodie.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class homeviewmodel(
    private val mealdatabase:database
):ViewModel() {
    private val randomMealLiveData = MutableLiveData<Meal>()
  private val popularitemLiveData = MutableLiveData<List<Categorymeals>>()
    private val categoryLiveData = MutableLiveData<List<Category>>()
    private val favouritemealdatabse = mealdatabase.mealdao().getAllmeals()
    fun getrandommeal(){
        RetrofitInstance.api.getrandommeal().enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if(response.body() != null){
                    val randommeal: Meal = response.body()!!.meals[0]
                    Log.d("test" , "${randommeal.idMeal} and ${randommeal.strArea}")
             randomMealLiveData.value = randommeal
                }
                else{
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("failed" , t.message.toString())
            }

        })
    }
    fun getpopularitems(){
        RetrofitInstance.api.getpopularitems("Seafood").enqueue(object : Callback<categorylist>{
            override fun onResponse(call: Call<categorylist>, response: Response<categorylist>) {
        if(response.body() != null){
            popularitemLiveData.value= response.body()!!.meals
        }
            }

            override fun onFailure(call: Call<categorylist>, t: Throwable) {

            }

        })
    }
    fun getcatogories(){
        RetrofitInstance.api.getcatogories().enqueue(object : Callback<catList>{
            override fun onResponse(call: Call<catList>, response: Response<catList>) {
               response.body()?.let { catList ->
                   categoryLiveData.postValue(catList.categories)
               }
            }

            override fun onFailure(call: Call<catList>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
    fun ObserverandommealLivedata() : LiveData<Meal>{
        return randomMealLiveData
    }
    fun ObserverpopularLivedata() : LiveData<List<Categorymeals>>{
        return popularitemLiveData
    }
    fun ObserveCategoryLivedata() : LiveData<List<Category>>{
        return categoryLiveData
    }
    fun Observefavlivedata() : LiveData<List<Meal>>{
        return favouritemealdatabse
    }
}