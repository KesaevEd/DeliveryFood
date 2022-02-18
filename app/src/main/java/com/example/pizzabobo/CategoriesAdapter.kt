package com.example.pizzabobo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzabobo.databinding.BannersItemBinding
import com.example.pizzabobo.databinding.CategoriesItemBinding
import java.util.*

class CategoriesAdapter:RecyclerView.Adapter<CategoriesAdapter.CategoriesHolder>() {

    val categoriesList = ArrayList<Category>()

    class CategoriesHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = CategoriesItemBinding.bind(item)
        fun bind(category: Category) = with(binding){
            categoryName.text = category.category_name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.categories_item, parent, false)
        return CategoriesHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        holder.bind(categoriesList[position])
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    fun addCategory(category: Category){
        categoriesList.add(category)
        notifyDataSetChanged()
    }
}