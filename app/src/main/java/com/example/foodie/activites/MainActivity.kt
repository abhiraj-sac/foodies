package com.example.foodie.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
//import com.example.foodie.Favfragment
//import com.example.foodie.Homefragment
import com.example.foodie.R
//import com.example.foodie.catagoriesFragment
import com.example.foodie.Favfragment
import com.example.foodie.Homefragment
import com.example.foodie.Viewmodel.Homeviewmodelfactory
import com.example.foodie.Viewmodel.homeviewmodel
import com.example.foodie.catagoriesFragment
import com.example.foodie.databasedb.database
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Locale.Category

class MainActivity : AppCompatActivity() {
    val viewmodel :homeviewmodel by lazy {
        val databse = database.getInstance(this)
        val homeviewmodelfactory = Homeviewmodelfactory(databse)
        ViewModelProvider(this,homeviewmodelfactory)[homeviewmodel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val bottomnavigation = findViewById<BottomNavigationView>(R.id.btm_nav)
//        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
//        NavigationUI.setupWithNavController(bottomnavigation,navController)
        replaceFragment(Homefragment())

        btm_nav.setOnItemSelectedListener {

            when(it.itemId){

                R.id.homefragment -> replaceFragment(Homefragment())
                R.id.favfragment -> replaceFragment(Favfragment())
                R.id.catagoriesFragment -> replaceFragment(catagoriesFragment())

                else ->{



                }

            }

            true

        }


    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment,fragment)
        fragmentTransaction.commit()


    }
    }
