package com.example.testpunchinandout

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.testpunchinandout.data.PunchClockResponse

class SharedViewModel : ViewModel()  {

    var workerInfo by mutableStateOf<PunchClockResponse?>(null)
        private set

    fun addPunchClockResponse(newPunchClockResponse: PunchClockResponse){
        workerInfo = newPunchClockResponse
    }
}