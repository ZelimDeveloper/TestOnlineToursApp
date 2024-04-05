package com.example.testonlinetoursapp.data.retrofit

import android.util.Log
import com.example.testonlinetoursapp.data.models.CityModel
import com.example.testonlinetoursapp.data.models.CountryModel
import com.example.testonlinetoursapp.data.models.CreateSearchRequestModel
import com.example.testonlinetoursapp.data.models.CreateSearchResponseModel
import com.example.testonlinetoursapp.data.models.SearchRequestModel
import com.example.testonlinetoursapp.data.models.ToursResultModel
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.request
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataProvider @Inject constructor(private val retrofit: RetrofitHelperClass) {

    fun getCities(success: (List<CityModel>) -> Unit, error: ()->Unit = {}, exception: (String)->Unit = {}) {
        retrofit.retrofitApi.getCities().request { response ->
            when (response) {
                is ApiResponse.Success -> {
                    success(response.data)
                }
                is ApiResponse.Failure.Error -> {
                    error()
                }
                is ApiResponse.Failure.Exception -> {
                    response.message?.let { exception(it) }
                }
            }
        }
    }

    fun getTours(searchKey:String, success: (List<ToursResultModel>) -> Unit, error: ()->Unit = {}, exception: (String)-> Unit = {}) {
        retrofit.retrofitApi.getTours(searchKey = searchKey).request { response ->
            when (response) {
                is ApiResponse.Success -> {
                    success(response.data.results)
                }
                is ApiResponse.Failure.Error -> {
                    error()
                }
                is ApiResponse.Failure.Exception -> {
                    response.message?.let { exception(it) }
                }
            }
        }
    }



    fun createSearch(success: (String) -> Unit, error: () -> Unit, selectedCityId:Int) {
        val model = CreateSearchRequestModel(
            search = SearchRequestModel(depart_city_id = selectedCityId)
        )

        retrofit.retrofitApi.createSearch(model).request { response ->
            when (response) {

                is ApiResponse.Success -> {
                    success(response.data.key)
                }
                is ApiResponse.Failure.Error -> {
                    error()
                    Log.d("TAGG", "error")
                }
                is ApiResponse.Failure.Exception -> {
                    error()
                    Log.d("TAGG", "${response}")
                }
            }
        }
    }


    fun getCountries(success: (List<CountryModel>) -> Unit, error: ()->Unit = {}, exception: (String)->Unit = {}) {
        retrofit.retrofitApi.getCountries().request { response ->
            when (response) {
                is ApiResponse.Success -> {
                    success(response.data)
                }
                is ApiResponse.Failure.Error -> {
                    error()
                }
                is ApiResponse.Failure.Exception -> {
                    response.message?.let { exception(it) }
                }
            }
        }
    }



}