package com.example.testpunchinandout.data

// used to store data that is sent to the server in punch in / punch out POST-request

data class PunchInOutResponse (
    val time: String,
    val email: String
)