package com.example.testpunchinandout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp



@Composable
fun CustomKey(letter: String, onClick: (String) -> Unit) {  // yksittäisen napin tekofunktio, ottaa vastaan Stringin (kirjain jota nappi edustaa)
    Button(                                                 // ja onClick funktion joka kutsutaan kun nappia painetaan
        onClick = { onClick(letter) },
        shape = RoundedCornerShape(50),  // pyöristys
        modifier = Modifier.padding(4.dp)
    ) {
        Text(letter)
    }
}

@Composable
fun QwertyKeyboard(onKeyClick: (String) -> Unit) { // lista riveistä ja riveillä olevista kirjaimista(näppäimistö)
    val rows = listOf(
        "1234567890",
        "QWERTYUIOP",
        "ASDFGHJKL",
        "ZXCVBNM@.-",
        // Katso tarviiko lisää rivejä
    )

    Column {  // joka rivi käydään läpi
        rows.forEach { row ->
            Row {
                row.forEach { letter ->  // ja joka kirjaimella tehdään oma nappi
                    CustomKey(letter.toString(), onClick = onKeyClick)
                }
            }
        }

    }
}


