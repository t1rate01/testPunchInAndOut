package com.example.testpunchinandout

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel()  {

    var workerInfo by mutableStateOf<WorkStatus?>(null)
        private set

    fun addWorkStatus(newWorkStatus: WorkStatus){
        workerInfo = newWorkStatus
    }
}