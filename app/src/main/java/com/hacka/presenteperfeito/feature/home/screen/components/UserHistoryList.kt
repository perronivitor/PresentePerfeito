package com.hacka.presenteperfeito.feature.home.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hacka.presenteperfeito.core.designSystem.PerfectGiftTheme
import com.hacka.presenteperfeito.feature.home.model.HistoryModel

@Composable
fun UserHistoryList(items: List<HistoryModel>) {

    if (items.isEmpty()) {
        EmptyListAnimation()
    } else {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = items,
                key = { item -> item.firstName }
            ) { item ->
                UserHistoryItem(item, item.firstName)
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun PreviewUserHistoryList() {

    val sampleItems = listOf(
        HistoryModel("Lucas Simão", ProgressStatus.Finalized),
        HistoryModel("Maria Pereira", ProgressStatus.Waiting),
        HistoryModel("João Campos", ProgressStatus.Canceled)
    )

    PerfectGiftTheme {
        UserHistoryList(sampleItems)
    }
}