package com.projects.ekene.pusher_location_feeds

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface Service {

    @POST("location")
    fun sendLocation(@Body coordinates: RequestBody): Call<String>
}