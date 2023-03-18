package com.example.foodie.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodie.Adapeters.Categorymealadapters
import com.example.foodie.Homefragment
import com.example.foodie.R
import com.example.foodie.Viewmodel.categorymealsviewmodels
import com.example.foodie.databinding.ActivityCategorymealsBinding
import com.example.foodie.projo.Categorymeals

class Categorymeals : AppCompatActivity() {
     lateinit var binding: ActivityCategorymealsBinding
      lateinit var categoryviewmodel:categorymealsviewmodels
     lateinit var categorymealadapter:Categorymealadapters
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

binding = ActivityCategorymealsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preparerecycler()
        categoryviewmodel= ViewModelProvider(this)[categorymealsviewmodels::class.java]
        categoryviewmodel.getmealsbycategory(intent.getStringExtra(Homefragment.MEAL_CATEGORY)!!)
        categoryviewmodel.observemealsLivedata().observe(this) { mealslist ->
            categorymealadapter.setmealslist(mealslist as ArrayList<Categorymeals>)

        }
    }

    private fun preparerecycler() {
        categorymealadapter = Categorymealadapters()
        binding.rvMeals.apply {
            layoutManager =GridLayoutManager(context, 2, GridLayoutManager.VERTICAL,false)
            adapter = categorymealadapter
        }
    }
}