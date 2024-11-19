package com.hacka.presenteperfeito.feature.signin.presentation.login.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.hacka.presenteperfeito.core.designSystem.PerfectGiftTheme
import com.hacka.presenteperfeito.core.designSystem.components.buttons.ProjectButton
import com.hacka.presenteperfeito.core.designSystem.components.buttons.ProjectButtonTypes
import com.hacka.presenteperfeito.core.di.AppModule
import com.hacka.presenteperfeito.feature.signin.presentation.login.uiState.LoginEvents
import com.hacka.presenteperfeito.feature.signin.presentation.login.viewModel.LoginViewModel
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
import org.koin.ksp.generated.module

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(viewModel: LoginViewModel = koinViewModel()) {
    val uiState = viewModel.uiState.collectAsState()
    uiState.value.event?.let { HandleEvents(event = it, onFinish = viewModel::clearEvents) }
    Scaffold(topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
            }
        }, title = { Text(text = "Top App Bar") })
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .background(color = MaterialTheme.colorScheme.primary)
                .fillMaxSize()
        ) {
            TextField(label = { Text(text = "Email") },
                value = uiState.value.email ?: "",
                onValueChange = { text ->
                    viewModel.setEmail(text)
                })
            TextField(label = { Text(text = "Password") },
                value = uiState.value.password ?: "",
                onValueChange = { text ->
                    viewModel.setPassword(text)
                })

            var isLoading by remember {
                mutableStateOf(false)
            }

            ProjectButton(modifier = Modifier.fillMaxWidth(),
                text = "Entrar",
                isLoading = isLoading,
                buttonType = ProjectButtonTypes.Secondary,
                onButtonClick = {
                    viewModel.doLogin()
                })
        }
    }
}

@Composable
fun HandleEvents(event: LoginEvents, onFinish: () -> Unit) {
    val context = LocalContext.current
    when (event) {
        is LoginEvents.LoginError -> Toast.makeText(context, event.message, Toast.LENGTH_SHORT)
            .show()

        is LoginEvents.LoginSuccessfully -> Unit //Navegar para home
    }
    onFinish()
}

@Preview
@Composable
private fun LoginScreenPreview() {
    PerfectGiftTheme {
        KoinApplication(application = {
            modules(AppModule().module)
        }) {
            LoginScreen()
        }
    }
}