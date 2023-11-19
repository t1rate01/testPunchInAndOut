package com.example.testpunchinandout

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PhonePunchScreen(
    navController: NavController,
    email: String,
    sharedViewModel: SharedViewModel
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                // TODO: API KUTSU TÄNNE
                navController.popBackStack()
            },
            modifier = Modifier
                .padding(8.dp)
        ) {

            Text( "Punch Out")
        }
        val status = sharedViewModel.workerInfo //testitulostukset
        Text("Firstname: ${status?.firstName}")
        Text("Lastname: ${status?.lastName}")
        Text("isAtWork: ${status?.isAtWork}")
        Text("Date: ${status?.date}")
        Text("received e-mail: $email")


    }
}