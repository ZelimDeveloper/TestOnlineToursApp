package com.example.testonlinetoursapp.data.models

data class ResponseToursResultModel(
    val results: List<ToursResultModel>
)

data class ToursResultModel(
    val offer: ToursModel
)

data class ToursModel(
    val id:String,
    val title:String,
    val original_price:PriceModel,
    val start_date:String,
    val original_name:String,
    val operator: OperatorModel
)


data class PriceModel(
    val price:Int,
    val currency:String
)

data class OperatorModel(
    val id : Int,
    val name: String,
    val image: String,
    val url: String,
)