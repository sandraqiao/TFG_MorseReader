package com.example.morsereader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.morsereader.ui.navigation.BottomNavBar
import com.example.morsereader.ui.navigation.Screen
import com.example.morsereader.ui.screens.*
import com.example.morsereader.ui.theme.MorseReaderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MorseReaderTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    Scaffold(
                        topBar = { TopTitleBanner(navController) },
                        bottomBar = { BottomNavBar(navController) }
                    ) { padding ->
                        NavHost(
                            navController = navController,
                            startDestination = Screen.Home.route,
                            modifier = Modifier.padding(padding)
                        ) {
                            composable(Screen.Home.route) { HomeScreen() }
                            composable(Screen.Generate.route) { GenerateScreen() }
                            composable(Screen.Interpret.route) { InterpretScreen() }
                            composable(Screen.Info.route) { InfoScreen() }
                        }
                    }
                }
            }
        }
    }
}