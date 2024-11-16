package com.hacka.presenteperfeito.core.designSystem.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hacka.presenteperfeito.core.designSystem.PerfectGiftTheme

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    isLoading: Boolean = false,
    onButtonClick: () -> Unit = {},
) {
    Button(
        modifier = modifier.height(64.dp),
        onClick = onButtonClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.White
        ),
        border = BorderStroke(width = 1.dp, color = Color.White),
        enabled = !isLoading
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = Color.White)
        } else {
            Text(
                text = text,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun SecondaryButtonPreview() {
    PerfectGiftTheme {
        SecondaryButton(
            text = "Login"
        )
    }
}

@Preview
@Composable
private fun SecondaryButtonLoadingPreview() {
    PerfectGiftTheme {
        SecondaryButton(
            text = "Login",
            isLoading = true
        )
    }
}