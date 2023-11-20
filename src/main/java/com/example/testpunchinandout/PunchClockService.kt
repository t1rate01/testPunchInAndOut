package com.example.testpunchinandout

import retrofit2.http.GET
import retrofit2.http.Path
import com.example.testpunchinandout.data.PunchClockResponse
import com.example.testpunchinandout.data.PunchInOutResponse

import retrofit2.http.Body
import retrofit2.http.POST

interface PunchClockService {
    @GET("api/report/punchclock/{email}")
    suspend fun getPunchClockResponse(@Path("email") email: String): PunchClockResponse

    @POST("api/report/punchin")
    suspend fun postPunchInResponse(@Body request : PunchInOutResponse) : String

    @POST("api/report/punchout")
    suspend fun postPunchOutResponse(@Body request : PunchInOutResponse) : String
}