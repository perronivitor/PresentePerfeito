package com.hacka.presenteperfeito.feature.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hacka.presenteperfeito.core.common.bottomNavigation.BottomNavigationBar
import com.hacka.presenteperfeito.core.designSystem.PerfectGiftTheme
import com.hacka.presenteperfeito.core.designSystem.darkOrange
import com.hacka.presenteperfeito.feature.home.model.HistoryModel
import com.hacka.presenteperfeito.feature.home.screen.components.ContentHome
import com.hacka.presenteperfeito.feature.home.screen.components.HeaderHome
import com.hacka.presenteperfeito.feature.home.screen.components.ProgressStatus

@Composable
fun HomeScreen(
    navController: NavController,
    userHistoryList: List<HistoryModel>,
    userName: String,
    modifier: Modifier = Modifier,
) {

    Scaffold(
        topBar = {
            HeaderHome(userName)
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .background(darkOrange),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            ContentHome(userHistoryList)
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun PreviewHomeScreen() {
    PerfectGiftTheme {
        val navController = rememberNavController()
        val sampleItems = listOf(
            HistoryModel("Lucas Simão", ProgressStatus.Finalized),
            HistoryModel("Maria Pereira", ProgressStatus.Waiting),
            HistoryModel("João Campos", ProgressStatus.Canceled)
        )
        val userName = "Lucas Simão"

        HomeScreen(navController, sampleItems, userName)
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun PreviewHomeScreenEmpty() {
    PerfectGiftTheme {
        val navController = rememberNavController()
        val sampleItems = listOf<HistoryModel>()
        val userName = "Lucas Simão"

        HomeScreen(navController, sampleItems, userName)
    }
}