package com.example.foodie.Adapeters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodie.databinding.PopularItemBinding
import com.example.foodie.projo.Categorymeals
import com.example.foodie.projo.Meal
import com.example.foodie.projo.categorylist

class mostpopularadapters(): RecyclerView.Adapter<mostpopularadapters.popularviewbinding>() {
    lateinit var onItemclicked:((Categorymeals) -> Unit)
     private var mealList = ArrayList<Categorymeals>()
    fun setmeals(mealList:ArrayList<Categorymeals>){
        this.mealList=mealList
        notifyDataSetChanged()
    }
    class popularviewbinding( val binding: PopularItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): popularviewbinding {
       return popularviewbinding(PopularItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: popularviewbinding, position: Int) {
            Glide.with(holder.itemView)
                .load(mealList[position].strMealThumb)
                .into(holder.binding.popularimages)

        holder.itemView.setOnClickListener{
            onItemclicked.invoke(mealList[position])
        }

    }

    override fun getItemCount(): Int {
return mealList.size
    }
}