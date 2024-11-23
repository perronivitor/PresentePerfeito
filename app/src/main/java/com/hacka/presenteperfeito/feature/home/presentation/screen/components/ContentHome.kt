package com.hacka.presenteperfeito.feature.home.presentation.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hacka.presenteperfeito.core.common.Loading
import com.hacka.presenteperfeito.core.common.extensions.KoinPreview
import com.hacka.presenteperfeito.core.designSystem.PerfectGiftTheme
import com.hacka.presenteperfeito.feature.home.presentation.uiState.InvestigationItem

@Composable
fun ContentHome(sampleItems: List<InvestigationItem>, loadingState: Loading) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color.White, shape = RoundedCornerShape(
                    topStart = 50.dp, topEnd = 50.dp, bottomStart = 0.dp, bottomEnd = 0.dp
                )
            )
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = "Histórico",
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(top = 25.dp, bottom = 25.dp)
            )

            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color.Gray.copy(alpha = 0.5f))
            )

            if (loadingState == Loading.FirstLoading) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            } else UserHistoryList(items = sampleItems)
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun DefaultPreviewContentHome() {

    val sampleItems = listOf(
        InvestigationItem(id = "", state = ProgressStatus.Waiting, "Lucas Simão"),
        InvestigationItem(id = "", state = ProgressStatus.Finalized, "Lucas Simão"),
        InvestigationItem(id = "", state = ProgressStatus.Canceled, "Lucas Simão"),
    )
    KoinPreview {
        PerfectGiftTheme {
            ContentHome(sampleItems, Loading.Complete)
        }
    }
}