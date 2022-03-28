package com.example.DeliveryFood.Interface

import com.example.DeliveryFood.Model.Categories
import com.example.DeliveryFood.Model.Products
import retrofit2.Call
import retrofit2.http.GET
import java.util.*

interface RetrofitServices {
    @GET("https://mcdonalds.ru/api/menu/category/sandwiches")
    fun getSandwichesList(): Call<Products>


    @GET("https://mcdonalds.ru/api/menu?includeProducts")
    fun getMenu(): Call<Categories>

}