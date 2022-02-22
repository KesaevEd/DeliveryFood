package com.example.pizzabobo.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzabobo.DB.ListItemFood
import com.example.pizzabobo.Model.Category
import com.example.pizzabobo.Model.Offers
import com.example.pizzabobo.Model.Products
import com.example.pizzabobo.Model.Sandwich
import com.example.pizzabobo.R
import com.squareup.picasso.Picasso

class FoodAdapter(val context: Context, private val sandwichesList: List<Sandwich>) :
    RecyclerView.Adapter<FoodAdapter.FoodHolder>() {


    class FoodHolder(item: View) : RecyclerView.ViewHolder(item) {
        val name: TextView = item.findViewById(R.id.title)
        val detailText: TextView = item.findViewById(R.id.description)
        val price: TextView = item.findViewById(R.id.price)
        val image: ImageView = item.findViewById(R.id.image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
        return FoodHolder(view)
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {

        Picasso.get().load("https://mcdonalds.ru" + sandwichesList[position].offers[0].previewPicture).into(holder.image)
        holder.name.text = sandwichesList[position].name
         var detailText= sandwichesList[position].detailText.toString()
        val re = Regex("[^%.А-Яа-я0-9 ]")
        detailText = re.replace(detailText, "")
        val result = detailText.dropWhile{
            it.isDigit()
        }
        holder.detailText.text = result
        holder.price.text = sandwichesList[position].offers[0].price.toString()
    }

    override fun getItemCount(): Int {
        return sandwichesList.size
    }


}