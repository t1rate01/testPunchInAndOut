package com.example.testpunchinandout

import retrofit2.http.GET
import retrofit2.http.Path
import com.example.testpunchinandout.data.PunchClockResponse

interface PunchClockService {
    @GET("api/report/punchclock/{email}")
    suspend fun getPunchClockResponse(@Path("email") email: String): PunchClockResponse
}