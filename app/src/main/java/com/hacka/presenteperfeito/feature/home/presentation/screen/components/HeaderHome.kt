package com.hacka.presenteperfeito.feature.home.presentation.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hacka.presenteperfeito.core.common.extensions.getFirstName
import com.hacka.presenteperfeito.core.designSystem.PerfectGiftTheme
import com.hacka.presenteperfeito.core.designSystem.darkOrange
import com.hacka.presenteperfeito.feature.home.presentation.screen.util.RoundedImageWithInitialsHeaderHome

@Composable
fun HeaderHome(userName: String) {

    Box(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .background(darkOrange)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    append("Olá, ")
                    pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                    append(getFirstName(userName))
                    pop()
                },
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            RoundedImageWithInitialsHeaderHome(userName)
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun PreviewHeaderHome() {
    PerfectGiftTheme {
        HeaderHome("Lucas Simão")
    }
}