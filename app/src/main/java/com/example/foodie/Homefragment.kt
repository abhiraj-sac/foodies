package com.example.foodie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.foodie.Adapeters.categoryadapter
import com.example.foodie.Adapeters.mostpopularadapters
import com.example.foodie.Viewmodel.homeviewmodel
import com.example.foodie.activites.MainActivity
import com.example.foodie.activites.MainActivity2
import com.example.foodie.databinding.FragmentHomefragmentBinding
import com.example.foodie.projo.Category
import com.example.foodie.projo.Categorymeals
import com.example.foodie.projo.Meal


class Homefragment : Fragment() {
private lateinit var binding:FragmentHomefragmentBinding
private lateinit var homemvvm:homeviewmodel
private lateinit var randommeal:Meal
private lateinit var mostpopularadapters: mostpopularadapters
private lateinit var categoryadapter:categoryadapter
companion object{
    const val MEAL_ID= "com.example.foodie.id"
    const val MEAL_NAME= "com.example.foodie.NAME"
    const val MEAL_THUMB= "com.example.foodie.THUMB"
    const val MEAL_CATEGORY = "com.example.foodie.category"
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
  homemvvm = (activity as MainActivity).viewmodel
        mostpopularadapters = mostpopularadapters()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomefragmentBinding.inflate(inflater ,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popularviewItemrecyclerview()
      homemvvm.getrandommeal()
        Observerandommeal()
        homemvvm.getpopularitems()
        observepopulatdata()
        preparerecyclervirecategory()
        homemvvm.getcatogories()
        ObservecategoriesLivedata()
        onclickoncategorylist()
        binding.randomimage.setOnClickListener{
            onrandommealclick()
        }
     onitemclicked()

    }

    private fun onclickoncategorylist() {
        categoryadapter.onItemclicked = { category ->
            val intent = Intent(activity , com.example.foodie.activites.Categorymeals::class.java)
            intent.putExtra(MEAL_CATEGORY, category.strCategory)
            startActivity(intent)

        }
    }

    private fun preparerecyclervirecategory() {
        categoryadapter = categoryadapter()
        binding.recViewCategories.apply {
            layoutManager= GridLayoutManager(context, 3, GridLayoutManager.VERTICAL,false)
    adapter = categoryadapter
        }
    }

    private fun ObservecategoriesLivedata() {
    homemvvm.ObserveCategoryLivedata().observe(viewLifecycleOwner , Observer { category ->
        categoryadapter.setcatogorylist(category as ArrayList<Category>)
        }
    )
    }

    private fun onitemclicked() {
        mostpopularadapters.onItemclicked = {meals ->
            val intent = Intent(activity, MainActivity2::class.java)
            intent.putExtra(MEAL_NAME , meals.strMeal)
            intent.putExtra(MEAL_ID , meals.idMeal)
            intent.putExtra(MEAL_THUMB , meals.strMealThumb)
            startActivity(intent)
        }
    }

    private fun popularviewItemrecyclerview() {
        binding.recyclerPopularitems.apply {
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            adapter = mostpopularadapters
        }
    }

    private fun observepopulatdata() {
        homemvvm.ObserverpopularLivedata().observe(viewLifecycleOwner
        ) {mealList ->
      mostpopularadapters.setmeals(mealList = mealList as ArrayList<Categorymeals>)
        }
    }

    private fun onrandommealclick() {
        val intent = Intent(activity , MainActivity2::class.java)
        intent.putExtra(MEAL_NAME,randommeal.strMeal)
        intent.putExtra(MEAL_ID,randommeal.idMeal)
        intent.putExtra(MEAL_THUMB,randommeal.strMealThumb)
        startActivity(intent)
    }

    private fun Observerandommeal(){
    homemvvm.ObserverandommealLivedata().observe(viewLifecycleOwner,object:Observer<Meal>{
        override fun onChanged(value: Meal) {
            Glide.with(this@Homefragment)
                .load(value.strMealThumb)
                .into(binding.imgrandommeal)
               this@Homefragment.randommeal = value
        }

    })
    }

}