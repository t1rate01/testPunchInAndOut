package com.example.testpunchinandout

import retrofit2.http.GET
import retrofit2.http.Path

interface PunchClockService {
    @GET("api/report/punchclock/{email}")
    suspend fun getPunchClockResponse(@Path("email") email: String): PunchClockResponse
}