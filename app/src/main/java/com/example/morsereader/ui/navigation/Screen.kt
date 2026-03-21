package com.example.morsereader.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Highlight
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen (val title: String, val route: String, val icon: ImageVector){
    object Home: Screen("Inicio", "home", Icons.Default.Home)
    object Generate: Screen("Generar", "generate", Icons.Default.Highlight)
    object Interpret: Screen("Interpretar", "interpret", Icons.Default.CameraAlt)
    object Info: Screen("Info", "info", Icons.Default.AutoStories)

    companion object {
        val bottomNavItems: List<Screen> by lazy {
            listOf(Home, Generate, Interpret, Info)
        }
    }
}
