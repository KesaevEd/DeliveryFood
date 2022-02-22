package com.example.pizzabobo.Common

import com.example.pizzabobo.Interface.RetrofitServices
import com.example.pizzabobo.Retrofit.RetrofitClient

object Common {
    private val BASE_URL = "https://mcdonalds.ru/api/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}