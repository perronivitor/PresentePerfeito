package com.hacka.presenteperfeito.feature.home.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hacka.presenteperfeito.core.designSystem.PerfectGiftTheme
import com.hacka.presenteperfeito.feature.home.model.HistoryModel

@Composable
fun ContentHome(sampleItems: List<HistoryModel>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(
                    topStart = 50.dp,
                    topEnd = 50.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
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

            UserHistoryList(items = sampleItems)

        }
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun DefaultPreviewContentHome() {

    val sampleItems = listOf(
        HistoryModel("Lucas Simão", ProgressStatus.Finalized),
        HistoryModel("Maria Pereira", ProgressStatus.Waiting),
        HistoryModel("João Campos", ProgressStatus.Canceled)
    )

    PerfectGiftTheme {
        ContentHome(sampleItems)
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun DefaultPreviewContentHomeEmpty() {

    val sampleItems = listOf<HistoryModel>()

    PerfectGiftTheme {
        ContentHome(sampleItems)
    }
}