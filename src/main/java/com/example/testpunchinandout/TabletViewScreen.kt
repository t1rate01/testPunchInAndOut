package com.example.testpunchinandout

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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

@Composable
fun TabletViewScreen(navController: NavController) {
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        TabletTextField(
            value = text,
            onValueChange = { newText -> text = newText },
            label = "Email"
        )
        Spacer(modifier = Modifier.height(40.dp))
        QwertyKeyboard(onKeyClick = { key ->   // kutsuu CustomKeyBoard.ktn funktioita, tekee näppäimistön
            // Lisää uusin painallus jonoon
            text += key
        })

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            // Backspace nappi
            Button(onClick = {   // poistaa viimeisen merkin
                if (text.isNotEmpty()) text = text.dropLast(1)
            }) {
                Text("\u232B") // backspace symboli
            }

            // Enter button
            Button(onClick = {
                //submitEmail(text)
                // Enter nappi, kutsuu samaa funktiota kuin puhelimen Enter nappi, eli submitEmail
                navController.navigate("tablet_punch_screen")
                Log.d("MainActivity", "Enter pressed with text: $text")
            }) {
                Text("Enter")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabletTextField(value: String, onValueChange: (String) -> Unit, label: String) { // tabletin tekstikenttä, perinteinen kirjotus blokattu
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        readOnly = true, // Estää norminäppäimistön
        singleLine = true,
        shape = MaterialTheme.shapes.medium
    )
}

private fun submitEmail(email: String) {
    Log.d("MainActivity", "Email: $email")  // TODO: GET REQUEST SERVERILLE, (TEKEMÄTTÄ SERVERIN PUOLELLA.)
    //TODO:  TULEE PALAUTTAMAAN BOOLEAN atWork TRUE / FALSE + etunimi ja sukunimi, JOKA KERTOO ETTÄ ONKO TÖISSÄ VAI EI, EI MITÄÄN MUUTA. Seuraava näkymä sen mukaan
    // TODO: tyyliin if atWork = false, näkymä jossa Punch In ja Cancel, ja tietty henkilön etunimi ja sukunimi otsikkona.
    // TODO: if atWork = true niin samanlainen näkymä mutta Punch Out ja Cancel ja sama otsikko
    // TODO: Molempiin tervehdykset kun Punch In "Have a great day" tai vastaava ja Punch Out "See you next time" tai jotain muuta masentavaa
    // TODO: Punch In ja Punch Out nappien painalluksesta tulee POST REQUEST SERVERILLE, (TEKEMÄTTÄ SERVERIN PUOLELLA).
    // TODO: Post sisällöksi aika (muodossa HH:MM:SS), jos Punch In niin startTime ja jos Punch Out niin endTime, server käsittelee atWork logiikan.
}