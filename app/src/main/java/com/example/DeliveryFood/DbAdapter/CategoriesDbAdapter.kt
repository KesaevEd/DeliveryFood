package com.example.DeliveryFood.DbAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.DeliveryFood.DB.ListItemCategories
import com.example.DeliveryFood.R

class CategoriesDbAdapter(var categoriesList: ArrayList<ListItemCategories>) :
    RecyclerView.Adapter<CategoriesDbAdapter.CategoriesHolder>() {


    class CategoriesHolder(item: View) : RecyclerView.ViewHolder(item) {
        val name: Button = item.findViewById(R.id.category_name)

        fun fillFromDb(title1: String?){
            name.text = title1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.categories_item, parent, false)
        return CategoriesHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        holder.fillFromDb(categoriesList[position].name)
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    fun updateAdapter(listItems: List<ListItemCategories>){
        categoriesList.clear()
        categoriesList.addAll(listItems)
        notifyDataSetChanged()
    }

}