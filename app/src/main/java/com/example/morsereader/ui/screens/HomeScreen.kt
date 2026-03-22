package com.example.morsereader.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.morsereader.ui.navigation.Screen

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(Screen.Home.title)
    }
}

/*MenuButton("Generate Morse") {
            navController.navigate(Screen.Generate.route)
        }
        MenuButton("Interpret Morse") {
            navController.navigate(Screen.Interpret.route)
        }
        MenuButton("About") {
            navController.navigate(Screen.Info.route)
       */