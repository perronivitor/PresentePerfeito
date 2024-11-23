package com.hacka.presenteperfeito.feature.home.presentation.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hacka.presenteperfeito.feature.home.presentation.uiState.InvestigationItem

@Composable
fun UserHistoryList(items: List<InvestigationItem>) {

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
                key = { item -> item.name }
            ) { item ->
                UserHistoryItem(item, item.name.split(" ").firstOrNull() ?: "")
            }
        }
    }
}