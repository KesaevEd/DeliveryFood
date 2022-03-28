package com.example.DeliveryFood.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.DeliveryFood.Model.Category
import com.example.DeliveryFood.R

class CategoriesAdapter(val context:Context, private val categoriesList: List<Category>):RecyclerView.Adapter<CategoriesAdapter.CategoriesHolder>() {

    class CategoriesHolder(item: View): RecyclerView.ViewHolder(item) {

        val categoryName: TextView = item.findViewById(R.id.category_name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.categories_item, parent, false)
        return CategoriesHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {

        holder.categoryName.text = categoriesList[position].title.toString()
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

}