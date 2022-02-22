package com.example.pizzabobo.Model

import com.google.gson.annotations.SerializedName

data class Categories (

    @SerializedName("categories") var categories : List<Category>
)

data class Category(
    @SerializedName("id") var id: Int?    = null,
    @SerializedName("sort") var sort: Int?    = null,
    @SerializedName("alias") var alias: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("pageTitle") var pageTitle: String? = null

)

