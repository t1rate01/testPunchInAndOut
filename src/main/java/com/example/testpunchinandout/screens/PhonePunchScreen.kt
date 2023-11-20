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


@Composable
fun PhonePunchScreen(
    navController: NavController,
    email: String,
    sharedViewModel: SharedViewModel
) {
    val status = sharedViewModel.workerInfo //käyttäjän tiedot muuttujaan sharedViewModelin kautta

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (status?.isAtWork == true) { // jos ollaan töissä
            Text("Welcome ${status?.firstName} ${status?.lastName} ")
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val punchData = PunchInOutResponse(time = "12:30:12", email = email) // TODO : KOVAKOODATTU DATA OIKEAKSI
                            Log.d("PhonePunchScreen", "$punchData Tässä on punchdata")
                            val response = RetrofitClient.service.postPunchOutResponse(punchData)
                            Log.d("PhonePunchScreen", "$response uloskirjaus toimii")
                        } catch (e: Exception) {
                            withContext(Dispatchers.Main) {
                                Log.e("PhonePunchScreen", "Network request failed", e)
                            }
                        }
                    }
                    // TODO: VIESTI KÄYTTÄJÄLLE "SEE YOU NEXT TIME"
                    navController.popBackStack() //takaisin edelliseen ruutuun
                },
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text("Punch Out")
            }
        } else { //jos ei olla töissä
            Text("Welcome ${status?.firstName} ${status?.lastName} ")
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val punchData = PunchInOutResponse(time = "12:30:12", email = email) // TODO : KOVAKOODATTU DATA OIKEAKSI
                            Log.d("PhonePunchScreen", "$punchData Tässä on punchdata")
                            val response = RetrofitClient.service.postPunchInResponse(punchData)
                            Log.d("PhonePunchScreen", "$response Sisäänkirjaus toimii")
                        } catch (e: Exception) {
                            withContext(Dispatchers.Main) {
                                Log.e("PhonePunchScreen", "Network request failed", e)
                            }
                        }
                    }
                    // TODO: VIESTI KÄYTTÄJÄLLE "HAVE A NICE DAY AT WORK"
                    navController.popBackStack() //takaisin edelliseen ruutuun
                },
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text("Punch in")
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

