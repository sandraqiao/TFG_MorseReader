package com.example.morsereader.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.morsereader.ui.navigation.Screen

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(Screen.Generate.title)
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