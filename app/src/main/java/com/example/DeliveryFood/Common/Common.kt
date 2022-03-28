package com.example.DeliveryFood.Common

import com.example.DeliveryFood.Interface.RetrofitServices
import com.example.DeliveryFood.Retrofit.RetrofitClient

object Common {
    private val BASE_URL = "https://mcdonalds.ru/api/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}