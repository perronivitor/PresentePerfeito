package com.hacka.presenteperfeito.core.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hacka.presenteperfeito.feature.home.screen.HomeScreen
import com.hacka.presenteperfeito.feature.inspection.presentation.InspectionScreen
import com.hacka.presenteperfeito.feature.signin.presentation.login.screen.LoginScreen
import com.hacka.presenteperfeito.feature.signup.presentation.SignUpScreen
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    object SplashRoute

    @Serializable
    object SignInRoute

    @Serializable
    object SignUpRoute

    @Serializable
    object BottomNavigationRoute

    @Serializable
    object HomeRoute

    @Serializable
    object InspectionScreen

    @Serializable
    object ProfileScreen
}

@Composable
fun PerfectGiftNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Route.BottomNavigationRoute
    ) {
        //TODO: Spash Screen

        composable<Route.SignInRoute> {
            LoginScreen(
                navController = navController,
                navigateToSignUp = {
                    navController.navigate(Route.SignUpRoute)
                },
                navigateToHome = {
                    navController.navigate(Route.HomeRoute)
                },
            )
        }

        composable<Route.SignUpRoute> {
            SignUpScreen(
                navigateToHome = {
                    navController.navigate(Route.BottomNavigationRoute)
                },
                navigateToSignIn = {
                    navController.navigate(Route.SignInRoute)
                }
            )
        }

        // Bottom Bar Graph
        navigation<Route.BottomNavigationRoute>(startDestination = Route.HomeRoute) {
            composable<Route.HomeRoute> {
                HomeScreen(
                    navController = navController,
                    userName = "",
                    userHistoryList = emptyList(),
                    navigateToProfile = {

                    },
                    navigateToInspection = {
                        navController.navigate(Route.InspectionScreen)
                    }
                )
            }

            composable<Route.InspectionScreen> {
                InspectionScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onNavigateToHome = {
                        navController.navigate(Route.HomeRoute)
                    }
                )
            }

            // TODO: Profile Screen
        }
    }
}


