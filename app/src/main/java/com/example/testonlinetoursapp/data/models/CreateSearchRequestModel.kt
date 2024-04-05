package com.example.testonlinetoursapp.data.models

data class CreateSearchRequestModel(
    val search: SearchRequestModel

)

data class SearchRequestModel(
    val depart_city_id : Int,
    val region_ids: List<Int> = listOf(1601),
    val start_from: String = "2018-09-17",
    val start_to: String = "2018-09-30",
    val duration_from:Int = 5,
    val duration_to:Int = 10,
    val adults:Int = 2
)


data class CreateSearchResponseModel(
    val key:String
)

