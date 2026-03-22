package com.example.morsereader.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
val DefaultColorScheme: ColorScheme = darkColorScheme(
    primary = PurplePrimary,
    secondary = PurpleSecondary,
    background = DarkBackground,
    surface = CardBackground,
    onPrimary = DarkText,
    onSecondary = DarkText,
    onBackground = LightText,
    onSurface = LightText
)

@Composable
fun MorseReaderTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DefaultColorScheme,
        typography = Typography,
        content = content
    )
}