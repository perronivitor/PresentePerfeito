package com.hacka.presenteperfeito.feature.home.screen.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hacka.presenteperfeito.core.designSystem.PerfectGiftTheme
import com.hacka.presenteperfeito.core.designSystem.darkOrange

@Composable
fun RoundedImageWithInitialsContentHome(userName: String) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(
                color = darkOrange,
                shape = RoundedCornerShape(75.dp)
            )
            .padding(8.dp)
            .size(50.dp)
    ) {
        Text(
            text = getInitials(userName),
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun PreviewRoundedImageWithInitialsContentHome() {
    PerfectGiftTheme {
        RoundedImageWithInitialsContentHome("Lucas Simão")
    }
}