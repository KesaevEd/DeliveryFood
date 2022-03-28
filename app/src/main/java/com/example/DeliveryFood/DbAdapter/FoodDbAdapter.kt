package com.example.DeliveryFood.DbAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.DeliveryFood.DB.ListItemFood
import com.example.DeliveryFood.R

class FoodDbAdapter(var foodList: ArrayList<ListItemFood>) :
    RecyclerView.Adapter<FoodDbAdapter.FoodHolder>() {


    class FoodHolder(item: View) : RecyclerView.ViewHolder(item) {
        val name: TextView = item.findViewById(R.id.title)
        val detailText: TextView = item.findViewById(R.id.description)
        val price: TextView = item.findViewById(R.id.price)


        fun fillFromDb(title1: String?, detailText1: String?, price1: String?){
            name.text = title1
            detailText.text = detailText1
            price.text = price1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FoodHolder(inflater.inflate(R.layout.food_item, parent, false))
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.fillFromDb(foodList[position].name, foodList[position].description, foodList[position].price)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun updateAdapter(listItems: List<ListItemFood>){
        foodList.clear()
        foodList.addAll(listItems)
        notifyDataSetChanged()
    }

}