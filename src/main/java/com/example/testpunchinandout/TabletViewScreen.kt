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
import com.example.testpunchinandout.RetrofitClient
import com.example.testpunchinandout.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.testpunchinandout.data.PunchClockResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabletViewScreen(navController: NavController,
                    SharedViewModel: SharedViewModel,) {
    var text by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }
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
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val result = submitEmail(text)
                        withContext(Dispatchers.Main) {
                            workerInfo = result
                            sharedViewModel.addPunchClockResponse(newPunchClockResponse = result)
                            navController.navigate("tablet_punch_screen/${text}")

                        }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            showDialog = true
                            dialogMessage = e.message ?: "Email not found"  // TODO: tarkista mitä näkyy
                        }
                    }
                }
            }) {
                Text("Enter")
            }
        }
    }
    if (showDialog) {  // TODO: katso miltä näyttää
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Error") },
            text = { Text(dialogMessage) },
            confirmButton = {
                Button(
                    onClick = { showDialog = false },
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("OK")
                }
            }
        )
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
            onFail("Email not found") // TODO: Pitääkö tähän laittaa juuri se virhe mitä servu sanoo vai voiko olla esim näin
            PunchClockResponse("", "", false, "")
        }
    }
}