package com.hacka.presenteperfeito.feature.signin.presentation.login.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hacka.presenteperfeito.R
import com.hacka.presenteperfeito.core.common.Loading
import com.hacka.presenteperfeito.core.common.bottomNavigation.BottomNavItem
import com.hacka.presenteperfeito.core.designSystem.PerfectGiftTheme
import com.hacka.presenteperfeito.core.designSystem.components.buttons.ProjectButton
import com.hacka.presenteperfeito.core.designSystem.components.buttons.ProjectButtonTypes
import com.hacka.presenteperfeito.core.designSystem.components.textFields.PrimaryTextField
import com.hacka.presenteperfeito.core.di.AppModule
import com.hacka.presenteperfeito.feature.signin.presentation.login.uiState.LoginEvents
import com.hacka.presenteperfeito.feature.signin.presentation.login.uiState.LoginUiState
import com.hacka.presenteperfeito.feature.signin.presentation.login.viewModel.LoginViewModel
import kotlinx.coroutines.launch
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
import org.koin.ksp.generated.module

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    navController: NavController,
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()
    val uiState: LoginUiState by viewModel.uiState.collectAsState()
    val loadingState: Loading by viewModel.loadingState.collectAsState()
    uiState.event?.let {
        HandlerEvent(events = it,
            navController = navController,
            onClearEvent = viewModel::clearEvents,
            showSnackBar = { title ->
                scope.launch {
                    snackBarHostState.showSnackbar(message = title)
                }
            })
    }

    Scaffold(containerColor = MaterialTheme.colorScheme.primary,
        contentWindowInsets = WindowInsets(left = 16.dp, right = 16.dp),
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    modifier = Modifier.size(132.dp, 135.dp),
                    painter = painterResource(id = R.drawable.logo_gift_ic),
                    contentDescription = ""
                )
                Text(style = MaterialTheme.typography.headlineMedium, text = "Presente Perfeito")
            }
            Column {
                PrimaryTextField(leftIcon = {
                    Icon(
                        tint = MaterialTheme.colorScheme.primary,
                        imageVector = Icons.Outlined.Email,
                        contentDescription = ""
                    )
                },
                    errorMessage = uiState.emailInvalidError?.let { stringResource(id = it) },
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.email ?: "",
                    onValueChange = { text ->
                        viewModel.setEmail(text)

                    })
                PrimaryTextField(leftIcon = {
                    Icon(
                        tint = MaterialTheme.colorScheme.primary,
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = ""
                    )
                },
                    errorMessage = uiState.passwordInvalidError?.let { stringResource(id = it) },
                    isPassword = true,
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.password ?: "",
                    onValueChange = { text ->
                        viewModel.setPassword(text)
                    })
            }
            ProjectButton(modifier = Modifier.fillMaxWidth(),
                isLoading = loadingState == Loading.Processing,
                text = "Entrar",
                buttonType = ProjectButtonTypes.Secondary,
                onButtonClick = {
                    viewModel.doLogin()
                })
            Text(text = "Não possui conta? Cadastre-se aqui")
            //TODO ONCLICK SIGNUP
        }
    }
}

@Composable
fun HandlerEvent(
    events: LoginEvents,
    navController: NavController,
    showSnackBar: (message: String) -> Unit,
    onClearEvent: () -> Unit,
) {
    val context = LocalContext.current
    when (events) {
        is LoginEvents.LoginError -> {
            Toast.makeText(context, events.message, Toast.LENGTH_SHORT).show()

        }

        is LoginEvents.LoginSuccessfully -> {
            navController.navigate(BottomNavItem.Home.route)
        }

        is LoginEvents.InvalidCredential -> {
            showSnackBar(stringResource(id = R.string.login_invalid_credential_message))
        }
    }
    onClearEvent()
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    KoinApplication(application = {
        modules(AppModule().module)
    }) {
        PerfectGiftTheme {
            LoginScreen(
                navController = rememberNavController(),
                navigateToSignUp = {},
                navigateToHome = {}
            )
        }
    }
}