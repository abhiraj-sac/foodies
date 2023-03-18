package com.example.foodie.activites
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.foodie.Homefragment
import com.example.foodie.R
import com.example.foodie.Viewmodel.MealViewModel
import com.example.foodie.Viewmodel.mealviewmodelfactory
import com.example.foodie.databasedb.database
import com.example.foodie.databinding.ActivityMain2Binding
import com.example.foodie.projo.Meal

class MainActivity2 : AppCompatActivity() {
    private lateinit var Binding:ActivityMain2Binding
    private lateinit var meal_id:String
    private lateinit var meal_name:String
    private lateinit var meal_Thumb:String
    private lateinit var mealmvvm:MealViewModel
    private lateinit var youtubelinks:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(Binding.root)
        val mealdatase = database.getInstance(this)
        val viewModelFactory = mealviewmodelfactory(mealdatase)
        mealmvvm = ViewModelProvider(this,viewModelFactory)[MealViewModel::class.java]
     getmealinfofromintent()
        onload()
        mealmvvm.getmealdeatil(meal_id)
        observemealLivedata()
        getimageload()
        Binding.youtubeicon.setOnClickListener{
            youtubelink()
        }
        onfavsaved()
    }

    private fun onfavsaved() {
        Binding.favbtn.setOnClickListener {
            mealtosavedata?.let {
                mealmvvm.insert(it)
                Toast.makeText(this, "saved man!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun youtubelink() {
        val intent = Intent(Intent.ACTION_VIEW , Uri.parse(youtubelinks) )
        startActivity(intent)
    }

    private fun onload(){
     Binding.progessbars.visibility = View.VISIBLE
        Binding.youtubeicon.visibility = View.INVISIBLE
      Binding.tvArea.visibility = View.INVISIBLE
      Binding.tvCategory.visibility = View.INVISIBLE
    }
  private fun onresponse(){
      Binding.progessbars.visibility = View.INVISIBLE
      Binding.youtubeicon.visibility = View.VISIBLE
      Binding.tvArea.visibility = View.VISIBLE
      Binding.tvCategory.visibility = View.VISIBLE
    }

    private var mealtosavedata:Meal?=null
    private fun observemealLivedata() {
        mealmvvm.ObserveMeallivedata().observe(this,object :Observer<Meal>{
            override fun onChanged(t: Meal?) {
                onresponse()
                val meal=t
                mealtosavedata = meal
           Binding.tvCategory.text = "Category : ${meal!!.strCategory}"
           Binding.tvArea.text = "Area : ${meal.strArea}"
           Binding.instructiontext.text = meal!!.strInstructions
                youtubelinks = meal.strYoutube.toString()
            }

        })
    }

    private fun getimageload() {
        Glide.with(applicationContext)
            .load(meal_Thumb)
            .into(Binding.imgMealDetail)
Binding.collapsingToolbar.title = meal_name
        Binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        Binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))

    }

    private fun getmealinfofromintent() {
        val intent=intent
        meal_id = intent.getStringExtra(Homefragment.MEAL_ID)!!
        meal_name = intent.getStringExtra(Homefragment.MEAL_NAME)!!
        meal_Thumb = intent.getStringExtra(Homefragment.MEAL_THUMB)!!
    }
}