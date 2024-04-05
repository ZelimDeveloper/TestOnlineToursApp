package com.example.testonlinetoursapp.data.retrofit

import android.util.Log
import com.example.testonlinetoursapp.data.models.CityModel
import com.example.testonlinetoursapp.data.models.CountryModel
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