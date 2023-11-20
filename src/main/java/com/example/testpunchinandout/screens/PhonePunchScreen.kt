package com.example.testpunchinandout.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.testpunchinandout.SharedViewModel

@Composable
fun PhonePunchScreen(
    navController: NavController,
    email: String,
    sharedViewModel: SharedViewModel
){
    val status = sharedViewModel.workerInfo //käyttäjän tiedot sharedViewModelin kautta

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome ${status?.firstName} ${status?.lastName} ")
        Button(
            onClick = {
                // TODO: API KUTSU TÄNNE
                // TODO: VIESTI KÄYTTÄJÄLLE "HAVE A NICE DAY AT WORK/SEE YOU NEXT TIME", JA SEN JÄLKEEN PALUU EDELLISEEN

                navController.popBackStack() //takaisin edelliseen ruutuun
            },
            modifier = Modifier
                .padding(8.dp)
        ) {

            if (status != null) {
                Text(if(status.isAtWork){ "Punch Out" } else { "Punch in"})
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