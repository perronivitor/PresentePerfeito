package com.hacka.presenteperfeito.feature.signin.presentation.login.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hacka.presenteperfeito.feature.signin.presentation.login.uiState.LoginEvents
import com.hacka.presenteperfeito.feature.signin.presentation.login.viewModel.LoginViewModel
import kotlinx.coroutines.Dispatchers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun LoginScreen(viewModel: LoginViewModel = viewModel()) {
    val uiState = viewModel.currentUiState.collectAsState()
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
                .fillMaxWidth()
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
            TextButton(modifier = Modifier.fillMaxWidth(), onClick = { viewModel.doLogin() }) {
                Text(text = "Login")
            }
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