package com.hacka.presenteperfeito.feature.inspection.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hacka.presenteperfeito.R
import com.hacka.presenteperfeito.core.common.extensions.KoinPreview
import com.hacka.presenteperfeito.core.designSystem.components.buttons.ProjectButton
import com.hacka.presenteperfeito.core.designSystem.components.buttons.ProjectButtonTypes
import com.hacka.presenteperfeito.core.designSystem.components.textFields.SecondaryTextField
import com.hacka.presenteperfeito.feature.inspection.presentation.uiState.InspectionFormEvent
import com.hacka.presenteperfeito.feature.inspection.presentation.uiState.InspectionFormEvent.Submit
import com.hacka.presenteperfeito.feature.inspection.presentation.uiState.InspectionFormEvent.SubmitError
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun InspectionScreen(
    viewModel: InspectionViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    onNavigateToHome: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsState()
    uiState.value.event?.let {
        HandleEvents(
            event = it,
            onFinish = viewModel::clearEvents,
            onNavigateToHome = onNavigateToHome
        )
    }
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primary)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(56.dp))

            Box {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.gift),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

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

                    Text(
                        text = stringResource(R.string.feature_inspection_header_title),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = stringResource(R.string.feature_inspection_header_subtitle),
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface

                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    HorizontalDivider()

                    Spacer(modifier = Modifier.height(12.dp))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_inspection_name),
                        value = uiState.value.name,
                        errorText = uiState.value.nameError?.let {
                            stringResource(
                                id = it,
                                stringResource(id = R.string.feature_inspection_name)
                            )
                        },
                        onValueChange = { viewModel.setName(it) }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_inspection_relationship),
                        value = uiState.value.relationship,
                        errorText = uiState.value.relationshipError?.let {
                            stringResource(
                                id = it,
                                stringResource(id = R.string.feature_inspection_relationship)
                            )
                        },
                        onValueChange = { viewModel.setRelationship(it) }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_inspection_occasion),
                        value = uiState.value.occasion,
                        errorText = uiState.value.occasionError?.let {
                            stringResource(
                                id = it,
                                stringResource(R.string.feature_inspection_occasion)
                            )
                        },
                        onValueChange = { viewModel.setOccasion(it) }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_inspection_phoneNumber),
                        value = uiState.value.phoneNumber,
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done,
                        errorText = uiState.value.phoneNumberError?.let { stringResource(id = it) },
                        onValueChange = { viewModel.setPhoneNumber(it) },
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    ProjectButton(
                        text = stringResource(id = R.string.feature_inspection_button),
                        buttonType = ProjectButtonTypes.Primary,
                        onButtonClick = { viewModel.doCreateInspection() }
                    )
                }
            }
        }
    }
}

@Composable
fun HandleEvents(
    event: InspectionFormEvent,
    onFinish: () -> Unit,
    onNavigateToHome: () -> Unit,
) {
    when (event) {
        is Submit -> onNavigateToHome.invoke()
        is SubmitError -> {
            Toast.makeText(
                LocalContext.current,
                "Erro no cadastro: ${event.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    onFinish()
}

@Preview
@Composable
private fun InspectionScreenPreview() {
    KoinPreview {
        InspectionScreen(
            onBackClick = {},
            onNavigateToHome = {}
        )
    }
}