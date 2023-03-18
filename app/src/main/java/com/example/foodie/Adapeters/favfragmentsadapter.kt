package com.example.foodie.Adapeters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodie.databinding.MealItemsBinding
import com.example.foodie.projo.Meal

class favfragmentsadapter: RecyclerView.Adapter<favfragmentsadapter.favviewholder>() {
    class favviewholder(val binding: MealItemsBinding):RecyclerView.ViewHolder(binding.root)
    private val diffutl = object :DiffUtil.ItemCallback<Meal>(){
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return (oldItem.idMeal == newItem.idMeal)
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,diffutl)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): favviewholder {
return  favviewholder(
    MealItemsBinding.inflate(
        LayoutInflater.from(parent.context),parent,false)
    )
    }

    override fun onBindViewHolder(holder: favviewholder, position: Int) {
val meallist = differ.currentList[position]
        Glide.with(holder.itemView)
            .load(meallist.strMealThumb)
            .into(holder.binding.itemmealimg)
//        holder.binding.itemmealname.text = meallist.strMeal
    }

    override fun getItemCount(): Int {
return differ.currentList.size
    }


}