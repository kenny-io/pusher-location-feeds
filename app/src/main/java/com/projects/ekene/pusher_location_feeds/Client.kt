package com.projects.ekene.pusher_location_feeds

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class Client {
    fun getClient(): Service {
        val httpClient = OkHttpClient.Builder()

        val builder = Retrofit.Builder()
                .baseUrl("https://wt-97b8897f620c6dc18f4ea6a53e0e0796-0.run.webtask.io/pusher-location-feeds")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

        val retrofit = builder
                .client(httpClient.build())
                .build()

        return retrofit.create(Service::class.java)
    }
}