package com.example.foodie.Adapeters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodie.databinding.CatogriesItemsBinding
import com.example.foodie.projo.Category
import com.example.foodie.projo.Categorymeals

class categoryadapter(): RecyclerView.Adapter<categoryadapter.catviewholder>() {
    private var categorylist = ArrayList<Category>()
    var onItemclicked : ((Category) -> Unit)? = null
    fun setcatogorylist(categorylist:ArrayList<Category>){
        this.categorylist=categorylist
        notifyDataSetChanged()
    }
    class catviewholder( val binding: CatogriesItemsBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): catviewholder {
        return catviewholder(
            CatogriesItemsBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: catviewholder, position: Int) {
  Glide.with(holder.itemView)
      .load(categorylist[position].strCategoryThumb)
      .into(holder.binding.categoriesimage)
    holder.binding.categorytext.text = categorylist[position].strCategory
        holder.itemView.setOnClickListener{
            onItemclicked!!.invoke(categorylist[position])
        }
    }

    override fun getItemCount(): Int {
return categorylist.size
    }
}