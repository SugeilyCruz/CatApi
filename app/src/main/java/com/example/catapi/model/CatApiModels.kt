package com.example.catapi.model

import com.google.gson.annotations.SerializedName

data class CatResponse(
    @SerializedName("id") var catId : String,
    @SerializedName("name") var catName : String,
    @SerializedName("reference_image_id") var imgId : String,
    @SerializedName("description") var description : String,
    @SerializedName("temperament") var temperament : String,
    @SerializedName("origin") var originCat : String,
    @SerializedName("wikipedia_url") var wikipediaurl : String
)
