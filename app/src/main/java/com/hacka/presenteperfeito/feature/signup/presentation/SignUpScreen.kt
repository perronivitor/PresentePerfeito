package com.hacka.presenteperfeito.feature.signup.presentation

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hacka.presenteperfeito.R
import com.hacka.presenteperfeito.core.common.extensions.KoinPreview
import com.hacka.presenteperfeito.core.designSystem.components.buttons.ProjectButton
import com.hacka.presenteperfeito.core.designSystem.components.buttons.ProjectButtonTypes
import com.hacka.presenteperfeito.core.designSystem.components.profile.ProfilePictureOptionModalBottomSheet
import com.hacka.presenteperfeito.core.designSystem.components.profile.ProfilePictureSelector
import com.hacka.presenteperfeito.core.designSystem.components.textAnnotated.AnnotatedString
import com.hacka.presenteperfeito.core.designSystem.components.textFields.SecondaryTextField
import com.hacka.presenteperfeito.feature.signup.presentation.uiState.SignUpFormEvent
import com.hacka.presenteperfeito.feature.signup.presentation.uiState.SignUpFormEvent.Submit
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = koinViewModel(),
    navigateToHome: () -> Unit,
    navigateToSignIn: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsState()
    uiState.value.event?.let {
        HandleEvents(
            event = it,
            onFinish = viewModel::clearEvents,
            onNavigateToHome = navigateToHome
        )
    }

    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primary)
            .verticalScroll(rememberScrollState()),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(56.dp))

            Image(
                painter = painterResource(id = R.drawable.gift),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                modifier = Modifier.fillMaxSize(),
                shape = MaterialTheme.shapes.extraLarge.copy(
                    bottomStart = CornerSize(0.dp),
                    bottomEnd = CornerSize(0.dp)
                ),
                color = MaterialTheme.colorScheme.surface
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProfilePictureSelector(
                        imageUri = uiState.value.profilePictureUri,
                        modifier = Modifier.clickable {
                            viewModel.setOpenProfilePictureModalBottomSheet()
                        }
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_sign_up_first_name),
                        value = uiState.value.firstName,
                        onValueChange = {
                            viewModel.setFirstName(it)
                        },
                        errorText = uiState.value.firstNameError?.let {
                            stringResource(
                                id = it,
                                stringResource(id = R.string.feature_sign_up_first_name)
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_sign_up_last_name),
                        value = uiState.value.lastName,
                        onValueChange = {
                            viewModel.setLastName(it)
                        },
                        errorText = uiState.value.lastNameError?.let {
                            stringResource(
                                id = it,
                                stringResource(id = R.string.feature_sign_up_last_name)
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_sign_up_email),
                        value = uiState.value.email,
                        onValueChange = {
                            viewModel.setEmail(it)
                        },
                        keyboardType = KeyboardType.Email,
                        errorText = uiState.value.emailError?.let { stringResource(id = it) }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_sign_up_password),
                        value = uiState.value.password,
                        onValueChange = {
                            viewModel.setPassword(it)
                        },
                        keyboardType = KeyboardType.Password,
                        extraText = uiState.value.passwordExtraTextId?.let { stringResource(id = it) },
                        errorText = uiState.value.passwordError?.let { stringResource(id = it) }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_sign_up_password_confirmation),
                        value = uiState.value.passwordConfirmation,
                        onValueChange = {
                            viewModel.setPasswordConfirmation(it)
                        },
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done,
                        extraText = uiState.value.passwordExtraTextId?.let { stringResource(id = it) },
                        errorText = uiState.value.passwordConfirmationError?.let { stringResource(id = it) }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    ProjectButton(
                        text = stringResource(id = R.string.feature_sign_up_button),
                        buttonType = ProjectButtonTypes.Primary,
                        onButtonClick = {
                            viewModel.doSubmit()
                        }
                    )
                }
            }

            HandleProfilePictureOptionModalBottomSheet(
                isProfilePictureModalBottomSheetOpen = uiState.value.isProfilePictureModalBottomSheetOpen,
                onSetProfilePicture = { viewModel.setProfilePictureUri(it) },
                onClose = { viewModel.setCloseProfilePictureModalBottomSheet() }
            )

            AnnotatedString(
                startText = stringResource(id = R.string.feature_sign_up_has_account),
                underlineText = stringResource(id = R.string.feature_sign_up_login_here),
                underlineTextColor = MaterialTheme.colorScheme.primary,
                startTextColor = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface),
                onUnderlineClick = { navigateToSignIn.invoke() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HandleProfilePictureOptionModalBottomSheet(
    isProfilePictureModalBottomSheetOpen: Boolean,
    onSetProfilePicture: (uri: Uri) -> Unit,
    onClose: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    if (isProfilePictureModalBottomSheetOpen) {
        ProfilePictureOptionModalBottomSheet(
            onPictureSelected = { uri ->
                onSetProfilePicture.invoke(uri)
                scope.launch {
                    sheetState.hide()
                }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        onClose.invoke()
                    }
                }
            },
            onDismissRequest = {
                onClose.invoke()
            },
            sheetState = sheetState
        )
    }
}

@Composable
fun HandleEvents(
    event: SignUpFormEvent,
    onFinish: () -> Unit,
    onNavigateToHome: () -> Unit,
) {
    when (event) {
        is Submit -> onNavigateToHome.invoke()
//        is SignUpFormEvent.SubmitError -> {
//            Toast.makeText(
//                LocalContext.current,
//                "Erro no cadastro: ${event.message}",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
    }
    onFinish()
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    KoinPreview {
        SignUpScreen(navigateToHome = {

        }, navigateToSignIn = {

        })
    }
}