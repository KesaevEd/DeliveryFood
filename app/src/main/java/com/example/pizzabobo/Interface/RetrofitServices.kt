package com.example.pizzabobo.Interface

import com.example.pizzabobo.Model.Categories
import com.example.pizzabobo.Model.Products
import retrofit2.Call
import retrofit2.http.GET
import java.util.*

interface RetrofitServices {
    @GET("https://mcdonalds.ru/api/menu/category/sandwiches")
    fun getSandwichesList(): Call<Products>


    @GET("https://mcdonalds.ru/api/menu?includeProducts")
    fun getMenu(): Call<Categories>

}