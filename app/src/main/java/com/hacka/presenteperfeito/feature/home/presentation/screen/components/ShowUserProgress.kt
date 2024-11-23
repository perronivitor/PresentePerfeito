package com.hacka.presenteperfeito.feature.home.presentation.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hacka.presenteperfeito.feature.home.presentation.uiState.InvestigationItem

@Composable
fun ShowUserProgress(item: InvestigationItem) {
    Text(
        text = item.state.label,
        color = Color.White,
        modifier = Modifier
            .width(100.dp)
            .background(
                color = item.state.color,
                shape = RoundedCornerShape(25.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp),
        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
        textAlign = TextAlign.Center
    )
}