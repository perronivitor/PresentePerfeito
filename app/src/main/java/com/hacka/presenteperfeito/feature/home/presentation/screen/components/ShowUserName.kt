package com.hacka.presenteperfeito.feature.home.presentation.screen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShowUserName(name: String) {
    BasicText(
        text = name,
        style = MaterialTheme.typography.bodyLarge.copy(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier.padding(bottom = 4.dp)
    )
}