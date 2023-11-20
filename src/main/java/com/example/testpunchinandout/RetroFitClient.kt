package com.example.testpunchinandout
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// creates a retrofit instance to handle API requests

object RetrofitClient {
    private const val BASE_URL = "https://workhoursapp-9a5bdf993d73.herokuapp.com/"

    val service: PunchClockService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PunchClockService::class.java)
    }
}