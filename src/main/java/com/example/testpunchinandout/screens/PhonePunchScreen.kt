package com.example.testpunchinandout.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.testpunchinandout.RetrofitClient
import com.example.testpunchinandout.SharedViewModel
import com.example.testpunchinandout.data.PunchInOutResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter  // TODO: toimiiko nämä importit



@Composable // TODO: muokattu käyttämään if isAtWork statusta ja muuttamaan siitä tekstejä ja toimintoja
fun PhonePunchScreen(
    navController: NavController,
    email: String,
    sharedViewModel: SharedViewModel
) {
    val status = sharedViewModel.workerInfo
    var snackbarMessage by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (status?.isAtWork == true) { // jos ollaan töissä
            Text("Welcome ${status?.firstName} ${status?.lastName} ")
            Button(onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                        val punchData = PunchInOutResponse(time = currentTime, email = email)
                        
                        val response = if (status.isAtWork) {
                            RetrofitClient.service.postPunchOutResponse(punchData)
                        } else {
                            RetrofitClient.service.postPunchInResponse(punchData)
                        }
            
                        withContext(Dispatchers.Main) {
                            if (response.isSuccessful) {
                                snackbarMessage = if (status.isAtWork) "See you next time" else "Have a nice day at work"
                                showSnackbar = true
                                delay(3000) // TODO: Chekkaa onko sopiva aika
                                navController.popBackStack() // Navigate back
                            } else {
                                snackbarMessage = "Error: ${response.errorBody()?.string() ?: "Unknown error"}"
                                showSnackbar = true
                            }
                        }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            snackbarMessage = "Network request failed: ${e.message}"
                            showSnackbar = true
                        }
                    }
                }
            }) {
                Text(if (status.isAtWork) "Punch Out" else "Punch In")
            }

            if (snackbarMessage.isNotEmpty()) {
                scope.launch {
                    snackbarHostState.showSnackbar(snackbarMessage)
                    snackbarMessage = "" // Reset message after showing
                }
            }
        
           
            
            //varmistellaan datan tila:
            Text("Firstname: ${status?.firstName}")
            Text("Lastname: ${status?.lastName}")
            Text("isAtWork: ${status?.isAtWork}")
            Text("Date: ${status?.date}")
            Text("received e-mail: $email")
        }
    }
    SnackbarHost(hostState = snackbarHostState)
}


