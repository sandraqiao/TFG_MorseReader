package com.example.morsereader.ui.navigation

sealed class Screen (val route: String){
    object Home: Screen("home")
    object Generate: Screen("generate")
    object Interpret: Screen("interpret")
    object Info: Screen("info")
}