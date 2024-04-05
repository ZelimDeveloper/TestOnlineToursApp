package com.example.testonlinetoursapp.data.models

import com.google.gson.annotations.SerializedName

data class CityModel(
    val id: Int,
    val name: String,
    @SerializedName("country_id")
    val countryId: Int,
    @SerializedName("country_name")
    val countryName: String,

)

data class CountryModel(
    val id: Int,
    val name: String
)
