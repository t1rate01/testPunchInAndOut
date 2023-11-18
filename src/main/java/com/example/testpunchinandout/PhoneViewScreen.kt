package com.example.testpunchinandout

import android.os.Parcelable
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
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class) // jotta voidaan käyttää Material3 kirjastoa
@Composable
fun PhoneViewScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel) {
    var email by remember { mutableStateOf("") }
    var workerInfo by remember { mutableStateOf(WorkStatus(false, "", "")) }


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
                workerInfo = submitEmail(email)  //Tähän vastaus backendistä
                sharedViewModel.addWorkStatus(newWorkStatus = workerInfo)
                Log.d("MainActivity", "info phoneView: $workerInfo")
                navController.navigate("phone_punch_screen/$email")
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

data class WorkStatus(
    val atWork: Boolean,
    val firstName: String,
    val lastName: String
)

private fun submitEmail(email: String): WorkStatus {
    Log.d("MainActivity", "Email: $email")
    // TODO: API KUTSU TÄHÄN VÄLIIN, GET REQUEST SERVERILLE, (TEKEMÄTTÄ SERVERIN PUOLELLA.)
    val atWork = false
    val firstName = "Keijo"
    val lastName = "Keijokainen"

    return WorkStatus(atWork,firstName,lastName)
    //TODO:  TULEE PALAUTTAMAAN BOOLEAN atWork TRUE / FALSE + etunimi ja sukunimi, JOKA KERTOO ETTÄ ONKO TÖISSÄ VAI EI, EI MITÄÄN MUUTA. Seuraava näkymä sen mukaan
    // TODO: tyyliin if atWork = false, näkymä jossa Punch In ja Cancel, ja tietty henkilön etunimi ja sukunimi otsikkona.
    // TODO: if atWork = true niin samanlainen näkymä mutta Punch Out ja Cancel ja sama otsikko
    // TODO: Molempiin tervehdykset kun Punch In "Have a great day" tai vastaava ja Punch Out "See you next time" tai jotain muuta masentavaa
    // TODO: Punch In ja Punch Out nappien painalluksesta tulee POST REQUEST SERVERILLE, (TEKEMÄTTÄ SERVERIN PUOLELLA).
    // TODO: Post sisällöksi aika (muodossa HH:MM:SS), jos Punch In niin startTime ja jos Punch Out niin endTime, server käsittelee atWork logiikan.
}