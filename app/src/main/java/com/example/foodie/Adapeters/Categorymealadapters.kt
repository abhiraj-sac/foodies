package com.example.foodie.Adapeters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodie.activites.Categorymeals
import com.example.foodie.databinding.ActivityCategorymealsBinding
import com.example.foodie.databinding.MealItemsBinding

class Categorymealadapters: RecyclerView.Adapter<Categorymealadapters.viewholder>() {
    private var meallist = ArrayList<com.example.foodie.projo.Categorymeals>()

  fun setmealslist(meallist:ArrayList<com.example.foodie.projo.Categorymeals>){
      this.meallist = meallist as ArrayList<com.example.foodie.projo.Categorymeals>
notifyDataSetChanged()}

      class viewholder( val binding: MealItemsBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(MealItemsBinding.inflate(
            LayoutInflater.from(parent.context)
        ))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
Glide.with(holder.itemView).load(meallist[position].strMealThumb).into(holder.binding.itemmealimg)
        holder.binding.itemmealname.text = meallist[position].strMeal
    }

    override fun getItemCount(): Int {
        return meallist.size
    }

}



