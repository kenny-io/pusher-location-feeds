package com.projects.ekene.pusher_location_feeds

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface Service {

    @GET("/{message}")
    fun sendMessage(@Path("message") title: String): Call<String>
}