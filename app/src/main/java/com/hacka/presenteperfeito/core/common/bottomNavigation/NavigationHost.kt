package com.hacka.presenteperfeito.core.common.bottomNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hacka.presenteperfeito.feature.home.screen.HomeScreen

@Composable
fun NavigationHost() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) { HomeScreen(navController, listOf(), "") }
        composable(BottomNavItem.Adds.route) { /* Search Screen UI */ }
        composable(BottomNavItem.Profile.route) { /* Profile Screen UI */ }
    }
}