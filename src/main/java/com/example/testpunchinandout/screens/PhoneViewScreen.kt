package com.example.testpunchinandout.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.testpunchinandout.RetrofitClient
import com.example.testpunchinandout.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.testpunchinandout.data.PunchClockResponse


@OptIn(ExperimentalMaterial3Api::class) // jotta voidaan käyttää Material3 kirjastoa
@Composable
fun PhoneViewScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    var email by remember { mutableStateOf("") }
    var workerInfo by remember { mutableStateOf(PunchClockResponse("", "", true, "")) }

    Column(modifier =
    Modifier
        .padding(16.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {newValue: String -> email = newValue},
            label = { Text("Email") },
            singleLine = true,
            shape = MaterialTheme.shapes.medium
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val result = submitEmail(email)
                        withContext(Dispatchers.Main) {
                            workerInfo = result
                            sharedViewModel.addPunchClockResponse(newPunchClockResponse = result)
                            Log.d("MainActivity", "info phoneView: $workerInfo")
                            navController.navigate("phone_punch_screen/$email")
                        }
                    } catch (e: Exception) {

                        withContext(Dispatchers.Main) {

                        }
                    }
                }
            },
            modifier = Modifier
                .padding(top = 8.dp)
                .size(200.dp, 50.dp),
            shape = MaterialTheme.shapes.medium

        ) {
            Text("Enter")
        }
    }
}

private suspend fun submitEmail(email: String): PunchClockResponse {
    return withContext(Dispatchers.IO) {
        try {

            val response = RetrofitClient.service.getPunchClockResponse(email)

            val firstName = response.firstName
            val lastName = response.lastName
            val isAtWork = response.isAtWork
            val date = response.date

            PunchClockResponse(firstName, lastName, isAtWork, date)
        } catch (e: Exception) {
            Log.e("MainActivity", "Network request failed", e)
            PunchClockResponse("", "", false, "")
        }
    }
}