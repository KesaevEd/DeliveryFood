package com.example.DeliveryFood.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.DeliveryFood.Model.Banner
import com.example.DeliveryFood.R
import com.example.DeliveryFood.databinding.BannersItemBinding

class BannersAdapter: RecyclerView.Adapter<BannersAdapter.BannersHolder>() {

    val bannersList = ArrayList<Banner>()

    class BannersHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = BannersItemBinding.bind(item)
        fun bind(banner: Banner) = with(binding){
            imageView.setImageResource(banner.imageId)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannersHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.banners_item, parent, false)
        return BannersHolder(view)
    }

    override fun onBindViewHolder(holder: BannersHolder, position: Int) {
        holder.bind(bannersList[position])
    }

    override fun getItemCount(): Int {
        return bannersList.size
    }

    fun addBanner(banner: Banner){
        bannersList.add(banner)
        notifyDataSetChanged()
    }
}