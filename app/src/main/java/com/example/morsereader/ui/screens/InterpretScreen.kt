package com.example.morsereader.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.morsereader.morse.MorseTranslator

@Composable
fun InterpretScreen(modifier: Modifier = Modifier) {

    var morseInput by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var showInfo by remember { mutableStateOf(false) }

    fun validateInput(input: String): Boolean {
        return input.all { it == '.' || it == '-' || it == ' ' || it == '/' }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Box {
                IconButton(onClick = { showInfo = true }) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Info"
                    )
                }

                DropdownMenu(
                    expanded = showInfo,
                    onDismissRequest = { showInfo = false }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Cómo usar Morse")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("• '.' = punto \n" +
                                "• '-' = raya \n" +
                                "• Espacio = separar letras \n" +
                                "• '/' = separar palabras")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Ej: \n ... --- ... → SOS")
                    }
                }
            }
        }

        OutlinedTextField(
            value = morseInput,
            onValueChange = {
                morseInput = it
                isError = !validateInput(it)

                if (!isError && morseInput.isNotBlank()) {
                    result = MorseTranslator.morseToText(morseInput)
                } else {
                    result = ""
                }
            },
            label = { Text("Introduce código Morse") },
            isError = isError,
            modifier = Modifier.fillMaxWidth()
        )

        if (isError) {
            Text(
                text = "Caracteres no válidos.",
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Resultado: $result")
    }
}