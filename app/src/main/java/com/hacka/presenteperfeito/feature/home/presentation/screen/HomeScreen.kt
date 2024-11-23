package com.hacka.presenteperfeito.feature.home.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hacka.presenteperfeito.core.common.Loading
import com.hacka.presenteperfeito.core.common.extensions.KoinPreview
import com.hacka.presenteperfeito.core.designSystem.PerfectGiftTheme
import com.hacka.presenteperfeito.core.designSystem.darkOrange
import com.hacka.presenteperfeito.feature.home.presentation.screen.components.ContentHome
import com.hacka.presenteperfeito.feature.home.presentation.screen.components.HeaderHome
import com.hacka.presenteperfeito.feature.home.presentation.viewModel.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    userName: String,
    modifier: Modifier = Modifier,
    navigateToProfile: () -> Unit,
    navigateToInspection: () -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val loadingState by viewModel.loadingState.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = modifier.background(darkOrange),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        HeaderHome(userName)
        ContentHome(uiState.listOfInvestigations, loadingState)
    }

}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun PreviewHomeScreen() {
    KoinPreview {
        PerfectGiftTheme {
            ContentHome(listOf(), Loading.Complete)
        }
    }
}