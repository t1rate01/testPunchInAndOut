package com.example.testpunchinandout.data

data class PunchClockResponse(
    val firstName: String,
    val lastName: String,
    val isAtWork: Boolean,
    val date: String
)