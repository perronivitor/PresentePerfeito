package com.hacka.presenteperfeito.core.common.bottomNavigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hacka.presenteperfeito.core.common.navigation.Route
import com.hacka.presenteperfeito.core.designSystem.PerfectGiftTheme
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class)
@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = remember {
        listOf(
            BottomNavItem.Home,
            BottomNavItem.Adds,
            BottomNavItem.Profile
        )
    }

    val currentRoute = navController
        .currentBackStackEntryAsState()
        .value?.destination?.route
    val homeRoute = Route.HomeRoute.serializer().descriptor.serialName

    if (currentRoute == homeRoute) {
        BottomNavigation(
            backgroundColor = Color.White,
            contentColor = Color.Black
        ) {
            items.forEach { item ->
                val isSelected = currentRoute == item.route

                BottomNavigationItem(
                    icon = { item.icon() },
                    label = { item.label?.let { Text(text = it) } },
                    selected = isSelected,
                    onClick = { navigateToRoute(navController, item) }
                )
            }
        }
    }
}

private fun navigateToRoute(
    navController: NavController,
    item: BottomNavItem<out Any>,
) {
    navController.navigate(item.route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PerfectGiftTheme {
        val navController = rememberNavController()
        BottomNavigationBar(navController)
    }
}