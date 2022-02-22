package com.example.pizzabobo.Model

import com.google.gson.annotations.SerializedName

data class Products(
    @SerializedName("products" ) var products : ArrayList<Sandwich> = arrayListOf()
)

data class Sandwich(
    @SerializedName("detailText"   ) var detailText   : String?           = null,
    @SerializedName("id"           ) var id           : Int?              = null,
    @SerializedName("name"         ) var name         : String?           = null,
    @SerializedName("offers"       ) var offers       : ArrayList<Offers> = arrayListOf(),
)

data class Offers(
    @SerializedName("previewPicture"    ) var previewPicture    : String?             = null,
    @SerializedName("price"             ) var price             : Int?                = null,
)


