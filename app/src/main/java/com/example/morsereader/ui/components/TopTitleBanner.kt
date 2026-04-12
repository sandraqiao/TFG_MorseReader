package com.example.morsereader.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.morsereader.ui.navigation.Screen
import com.example.morsereader.ui.theme.CardBackground
import com.example.morsereader.ui.theme.DarkText

@Composable
fun TopTitleBanner(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val currentScreen = Screen.bottomNavItems.find {
        it.route == currentRoute
    } ?: Screen.Home

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        color = CardBackground,
        tonalElevation = 3.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = currentScreen.icon,
                contentDescription = currentScreen.title,
                tint = DarkText
            )

            Text(
                text = currentScreen.title,
                style = MaterialTheme.typography.titleLarge,
                color = DarkText,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}