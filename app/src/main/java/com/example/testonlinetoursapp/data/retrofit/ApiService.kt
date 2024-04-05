package com.example.testonlinetoursapp.data.retrofit

import com.example.testonlinetoursapp.constants.AUTH_TOKEN
import com.example.testonlinetoursapp.data.models.CityModel
import com.example.testonlinetoursapp.data.models.CountryModel
import com.example.testonlinetoursapp.data.models.CreateSearchRequestModel
import com.example.testonlinetoursapp.data.models.CreateSearchResponseModel
import com.example.testonlinetoursapp.data.models.ResponseToursResultModel
import com.example.testonlinetoursapp.data.models.SearchRequestModel
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("depart_cities")
    fun getCities(): Call<List<CityModel>>

    @GET("countries")
    fun getCountries(): Call<List<CountryModel>>

    @POST("searches")
    fun createSearch(@Body body: CreateSearchRequestModel): Call<CreateSearchResponseModel>


    @GET("searches/{key}/results")
    fun getTours(@Path("key") searchKey: String): Call<ResponseToursResultModel>





//    @POST("client/$API_UID/calculate_delivery")
//    fun calculateDeliveryPrice(@Body body: JsonObject):Call<Any>
//
//    @POST("client/$API_UID/push_notifications/receive/{id}")
//    fun receiveNotification(@Path("id") id:Int):Call<Any>
//
//
//    @Headers("Authorization: Token $ADDRESS_API_KEY")
//    @POST("$ADDRESS_URL/4_1/rs/suggest/address")
//    fun getAddresses(@Body body: AddressRequestModel): Call<AddressResponseModel>
//
//    @POST("client/$API_UID/attach_token")
//    fun attachFirebaseToken(@Body body: JsonObject):Call<Any>






}


