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
        startDestination = "StartPageScreen",
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = "HomeScreen") {
            HomeScreen(navigate = navController::navigate)
        }
        composable(route = "StartPageScreen") {
            StartPageScreen(navigate = navController::navigate)
        }
        composable(route = "ChooseRoleScreen") {
            ChooseRoleScreen(navigate = navController::navigate)
        }
        composable(route = "SignUpOrganizationScreen") {
            SignUpOrganizationScreen(navigate = navController::navigate)
        }
        composable(route = "SignUpUserScreen") {
            SignUpUserScreen(navigate = navController::navigate)
        }
        composable(route = "SignInScreen") {
            SignInScreen(navigate = navController::navigate)
        }
        composable(route = "UserProfileScreen") {
            UserProfileScreen(navigate = navController::navigate)
        }
        composable(route = "OrganizationProfileScreen") {
            OrganizationProfileScreen(navigate = navController::navigate)
        }
    }
}