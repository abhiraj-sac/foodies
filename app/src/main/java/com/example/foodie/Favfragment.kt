package com.example.foodie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodie.Adapeters.favfragmentsadapter
import com.example.foodie.R
import com.example.foodie.Viewmodel.homeviewmodel
import com.example.foodie.activites.MainActivity
import com.example.foodie.databinding.FragmentFavfragmentBinding

class Favfragment : Fragment() {
    private lateinit var homemvvm: homeviewmodel
    private lateinit var binding: FragmentFavfragmentBinding
    private lateinit var favfragmentsadapter: favfragmentsadapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homemvvm = (activity as MainActivity).viewmodel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavfragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ObservemealFav()
        preparefavadapter()
    }

    private fun preparefavadapter() {
        favfragmentsadapter = favfragmentsadapter()
        binding.recfav.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter=favfragmentsadapter
        }
    }

    private fun ObservemealFav() {
        homemvvm.Observefavlivedata().observe( viewLifecycleOwner, Observer {meals ->
         favfragmentsadapter.differ.submitList(meals)
        })
    }


}