@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.numberguessinggame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberGuessingGame()
        }
    }
}

@Composable
fun NumberGuessingGame() {
    var secretNumber by remember { mutableStateOf(Random.nextInt(1, 1001)) }
    var guess by remember { mutableStateOf(TextFieldValue()) }
    var message by remember { mutableStateOf("") }
    var attempts by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Number Guessing Game", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Guess the Number from 1-1000", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = guess,
            onValueChange = { guess = it },
            label = { Text(text = "Enter your guess number") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val userGuess = guess.text.toIntOrNull()
                if (userGuess != null) {
                    attempts++
                    when {
                        userGuess < secretNumber -> message = "It’s lower!"
                        userGuess > secretNumber -> message = "It’s higher!"
                        else -> message = "Congratulations! You guessed it in $attempts attempts."
                    }
                } else {
                    message = "Please enter a valid number."
                }
            }
        ) {
            Text(text = "Guess")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = message, color = if (message.contains("Congratulations")) Color.Green else Color.Red)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                secretNumber = Random.nextInt(1, 1001)
                guess = TextFieldValue()
                message = ""
                attempts = 0
            }
        ) {
            Text(text = "Play Again")
        }
    }
}