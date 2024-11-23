package com.hacka.presenteperfeito.feature.home.presentation.screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hacka.presenteperfeito.feature.home.presentation.screen.util.RoundedImageWithInitialsContentHome
import com.hacka.presenteperfeito.feature.home.presentation.uiState.InvestigationItem

@Composable
fun UserHistoryItem(item: InvestigationItem, userName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        RoundedImageWithInitialsContentHome(userName)

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            ShowUserName(userName)
            ShowUserProgress(item)
        }

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Avançar",
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )
    }
}