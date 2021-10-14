package com.example.headsup2

import retrofit2.Call
import retrofit2.http.*

interface APIInterface {
    
    @Headers("Content-Type: application/json")
    @GET("/celebrities/")
    fun getCelebrityDetails(): Call<List<CelebrityDetails.Datum>>

}