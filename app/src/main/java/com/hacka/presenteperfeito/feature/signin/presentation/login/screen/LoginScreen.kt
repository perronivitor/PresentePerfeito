package com.hacka.presenteperfeito.feature.signin.presentation.login.screen

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
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hacka.presenteperfeito.R
import com.hacka.presenteperfeito.core.designSystem.PerfectGiftTheme
import com.hacka.presenteperfeito.core.designSystem.components.textFields.PrimaryTextField
import com.hacka.presenteperfeito.feature.signin.presentation.login.uiState.LoginUiState
import com.hacka.presenteperfeito.feature.signin.presentation.login.viewModel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun LoginScreen(viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val uiState: LoginUiState by viewModel.currentUiState.collectAsState()
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


            Button(modifier = Modifier.fillMaxWidth(), onClick = { /*TODO*/ }) {
                Text(text = "Entrar")

            }

            Spacer(modifier = Modifier.height(57.dp))

            Text(text = "Não possui conta? Cadastre-se aqui")

        }

    }

}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    PerfectGiftTheme {
        LoginScreen()
    }

}

