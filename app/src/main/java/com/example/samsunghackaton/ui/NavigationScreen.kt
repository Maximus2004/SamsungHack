package com.example.samsunghackaton.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationScreen(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = "HomeScreen",
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = "HomeScreen") {
            HomeScreen()
        }
    }
}