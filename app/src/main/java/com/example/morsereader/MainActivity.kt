package com.example.morsereader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.morsereader.ui.navigation.BottomNavBar
import com.example.morsereader.ui.navigation.Screen
import com.example.morsereader.ui.screens.GenerateScreen
import com.example.morsereader.ui.screens.HomeScreen
import com.example.morsereader.ui.screens.InfoScreen
import com.example.morsereader.ui.screens.InterpretScreen
import com.example.morsereader.ui.components.TopTitleBanner
import com.example.morsereader.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background) {
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