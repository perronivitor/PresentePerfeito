package com.hacka.presenteperfeito.feature.signin.presentation.login.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hacka.presenteperfeito.R
import com.hacka.presenteperfeito.core.common.bottomNavigation.BottomNavItem
import com.hacka.presenteperfeito.core.designSystem.PerfectGiftTheme
import com.hacka.presenteperfeito.core.designSystem.components.buttons.ProjectButton
import com.hacka.presenteperfeito.core.designSystem.components.buttons.ProjectButtonTypes
import com.hacka.presenteperfeito.core.designSystem.components.textFields.PrimaryTextField
import com.hacka.presenteperfeito.core.di.AppModule
import com.hacka.presenteperfeito.feature.signin.presentation.login.uiState.LoginEvents
import com.hacka.presenteperfeito.feature.signin.presentation.login.uiState.LoginUiState
import com.hacka.presenteperfeito.feature.signin.presentation.login.viewModel.LoginViewModel
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
import org.koin.ksp.generated.module

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun LoginScreen(viewModel: LoginViewModel = koinViewModel(), navController: NavController) {
    val uiState: LoginUiState by viewModel.uiState.collectAsState()
    uiState.event?.let {
        HandlerEvent(
            events = it,
            navController = navController,
            onClearEvent = viewModel::clearEvents
        )
    }
    Scaffold(
        containerColor = MaterialTheme.colorScheme.primary,
        contentWindowInsets = WindowInsets(left = 16.dp, right = 16.dp)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(84.dp))

            Image(

                modifier = Modifier.size(132.dp, 135.dp),
                painter = painterResource(id = R.drawable.logo_gift_ic),
                contentDescription = ""
            )
            Text(style = MaterialTheme.typography.headlineMedium, text = "Presente Perfeito")

            Spacer(modifier = Modifier.height(87.dp))


            PrimaryTextField(leftIcon = {
                Icon(
                    tint = MaterialTheme.colorScheme.primary,
                    imageVector = Icons.Outlined.Email,
                    contentDescription = ""
                )
            },
                modifier = Modifier.fillMaxWidth(),
                value = uiState.email ?: "",
                onValueChange = { text ->
                    viewModel.setEmail(text)

                })

            Spacer(modifier = Modifier.height(14.dp))

            PrimaryTextField(leftIcon = {
                Icon(
                    tint = MaterialTheme.colorScheme.primary,
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = ""
                )
            },
                isPassword = true,
                modifier = Modifier.fillMaxWidth(),
                value = uiState.password ?: "",
                onValueChange = { text ->
                    viewModel.setPassword(text)
                })
            Spacer(modifier = Modifier.height(97.dp))


            ProjectButton(
                modifier = Modifier.fillMaxWidth(),
                text = "ENTRAR",
                buttonType = ProjectButtonTypes.Secondary,
                onButtonClick = {
                    viewModel.doLogin()
                }
            )

            Spacer(modifier = Modifier.height(57.dp))

            Text(text = "Não possui conta? Cadastre-se aqui")

        }

    }

}

@Composable
fun HandlerEvent(events: LoginEvents, navController: NavController, onClearEvent: () -> Unit) {
    val context = LocalContext.current
    when (events) {
        is LoginEvents.LoginError -> {
            Toast.makeText(context, events.message, Toast.LENGTH_SHORT).show()

        }

        is LoginEvents.LoginSuccessfully -> {
            navController.navigate(BottomNavItem.Home.route)
        }
    }

}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    KoinApplication(application = {
        modules(AppModule().module)

    }) {
        PerfectGiftTheme {
            LoginScreen(navController = rememberNavController())
        }
    }


}

