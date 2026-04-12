package com.example.morsereader.ui.screens

import MorseViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.morsereader.morse.MorseTranslator
import androidx.compose.ui.platform.LocalContext
import com.example.morsereader.morse.FlashController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.Alignment
import com.example.morsereader.morse.MorseMessage
import com.example.morsereader.ui.components.MessageDialog


@Composable
fun GenerateScreen(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val flashController = remember { FlashController(context) }

    val viewModel: MorseViewModel = viewModel()
    val messages by viewModel.messages.collectAsState(initial = emptyList())

    Box(modifier = modifier) {
        var showDialog by remember { mutableStateOf(false) }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyColumn {
                items(messages) { message ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        onClick = {
                            val morse = MorseTranslator.textToMorse(message.text)
                            flashController.emitMorse(morse)
                        }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {

                            Text(text = message.title)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = message.text)
                        }
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = {
                showDialog = true
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Añadir")
        }

        if (showDialog) {
            MessageDialog(
                onDismiss = { showDialog = false },
                onAdd = { title, text ->
                    viewModel.add(MorseMessage(title = title, text = text))
                    showDialog = false
                }
            )
        }
    }
}