package com.example.morsereader.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.morsereader.morse.MorseTranslator

@Composable
fun InterpretScreen(modifier: Modifier = Modifier) {

    var morseInput by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = morseInput,
            onValueChange = { morseInput = it },
            label = { Text("Introduce código Morse") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                result = MorseTranslator.morseToText(morseInput)
            }
        ) {
            Text("Traducir")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Resultado: $result")
    }
}