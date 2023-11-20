package com.example.testpunchinandout.data

// used to store user data that is fetched with GET from heroku database

data class PunchClockResponse(
    val firstName: String,
    val lastName: String,
    val isAtWork: Boolean,
    val date: String
)