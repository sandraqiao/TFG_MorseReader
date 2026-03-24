package com.example.morsereader.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.morsereader.morse.MorseTranslator
import androidx.compose.ui.platform.LocalContext
import com.example.morsereader.morse.FlashController

@Composable
fun GenerateScreen(modifier: Modifier = Modifier) {

    var morseText by remember { mutableStateOf("") }
    val context = LocalContext.current
    val flashController = remember { FlashController(context) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = {
            flashController.emitMorse(MorseTranslator.textToMorse("SOS"))
        }) {
            Text("Enviar SOS")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            flashController.emitMorse(MorseTranslator.textToMorse("HOLA"))
        }) {
            Text("Decir HOLA")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = morseText)
    }
}