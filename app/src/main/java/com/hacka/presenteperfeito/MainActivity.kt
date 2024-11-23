package com.hacka.presenteperfeito

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.hacka.presenteperfeito.core.auth.data.useCase.AuthUseCase
import com.hacka.presenteperfeito.core.common.bottomNavigation.BottomNavigationBar
import com.hacka.presenteperfeito.core.common.localData.dataStore.repository.dataStore
import com.hacka.presenteperfeito.core.common.navigation.PerfectGiftNavHost
import com.hacka.presenteperfeito.core.designSystem.PerfectGiftTheme
import com.hacka.presenteperfeito.core.designSystem.components.loading.FullScreenLoading
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Provides cache
        runBlocking { dataStore.data.firstOrNull() }
        enableEdgeToEdge()
        setContent {
            val authUseCase = koinInject<AuthUseCase>()
            val navController = rememberNavController()
            val scope = rememberCoroutineScope()
            var isAuthenticated by remember {
              mutableStateOf<Boolean?>(null)
            }
            scope.launch {
                authUseCase.isAuthenticated().collect { auth ->
                    isAuthenticated = auth
                }
            }
            PerfectGiftTheme {
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(navController)
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .consumeWindowInsets(innerPadding)
                            .padding(innerPadding)
                            .imePadding()
                            .fillMaxSize()
                    ) {
                        if(isAuthenticated == null) {
                            FullScreenLoading()
                        } else PerfectGiftNavHost(navController, needLogin = isAuthenticated == false)
                    }
                }
            }
        }
    }
}