package com.example.morsereader.ui.screens

import MorseViewModel
import androidx.compose.foundation.background
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
import androidx.compose.material3.*
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight


@Composable
fun GenerateScreen(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val flashController = remember { FlashController(context) }
    val isPlaying by flashController.isPlaying.collectAsState()

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
                items(messages, key = { it.id }) { message ->

                    val dismissState = rememberSwipeToDismissBoxState(
                        confirmValueChange = { value ->
                            if (value == SwipeToDismissBoxValue.EndToStart ||
                                value == SwipeToDismissBoxValue.StartToEnd
                            ) {
                                viewModel.delete(message)
                                true
                            } else {
                                false
                            }
                        }
                    )
                    SwipeToDismissBox(
                        state = dismissState,
                        backgroundContent = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.error,
                                        shape = MaterialTheme.shapes.medium
                                    ),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Text(
                                    text = "Borrar",
                                    color = MaterialTheme.colorScheme.onError,
                                    modifier = Modifier.padding(end = 16.dp)
                                )
                            }
                        }
                    ) {
                        Card(
                            shape = MaterialTheme.shapes.medium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.tertiaryContainer
                            ),
                            onClick = {
                                val morse = MorseTranslator.textToMorse(message.text)
                                flashController.emitMorse(morse)
                            }
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(text = message.title,
                                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = message.text,
                                    color = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        }
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = {
                showDialog = true
            },
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
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

        if (isPlaying) {
            FloatingActionButton(
                onClick = { flashController.stop() },
                containerColor = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Parar reproducción",
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}