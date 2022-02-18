package com.example.pizzabobo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzabobo.databinding.FoodItemBinding

class FoodAdapter : RecyclerView.Adapter<FoodAdapter.FoodHolder>() {

    val foodList = ArrayList<Food>()

    class FoodHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = FoodItemBinding.bind(item)
        fun bind(food: Food) = with(binding) {
            image.setImageResource(food.imageID)
            title.text = food.title
            description.text = food.description
            price.text = food.price
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
        return FoodHolder(view)
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.bind(foodList[position])
    }

    override fun getItemCount(): Int {
        return foodList.size
    }


    fun addFood(food: Food) {
        foodList.add(food)
        notifyDataSetChanged()
    }
}