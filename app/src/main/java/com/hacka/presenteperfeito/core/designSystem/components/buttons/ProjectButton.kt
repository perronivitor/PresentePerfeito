package com.hacka.presenteperfeito.core.designSystem.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import com.hacka.presenteperfeito.core.designSystem.components.buttons.ProjectButtonTypes.Primary
import com.hacka.presenteperfeito.core.designSystem.components.buttons.ProjectButtonTypes.Secondary

@Composable
fun ProjectButton(
    modifier: Modifier = Modifier,
    text: String,
    isLoading: Boolean = false,
    onButtonClick: () -> Unit = {},
    buttonType: ProjectButtonTypes = Primary,
) {
    Button(
        modifier = modifier.height(64.dp),
        onClick = onButtonClick,
        colors = buttonType.getColors(),
        enabled = !isLoading,
        border = if (buttonType is Secondary) BorderStroke(
            width = 1.dp,
            color = Color.White
        ) else null,
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

@Composable
private fun ProjectButtonTypes.getColors(): ButtonColors {
    return when (this) {
        is Primary -> {
            ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                disabledContentColor = Color.White
            )
        }

        is Secondary -> {
            ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.White,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun PrimaryButtonPreview() {
    PerfectGiftTheme {
        ProjectButton(
            text = "Login",
            buttonType = Primary
        )
    }
}

@Preview
@Composable
private fun PrimaryButtonLoadingPreview() {
    PerfectGiftTheme {
        ProjectButton(
            text = "Login",
            isLoading = true,
            buttonType = Primary
        )
    }
}

@Preview
@Composable
private fun SecondaryButtonPreview() {
    PerfectGiftTheme {
        ProjectButton(
            text = "Login",
            buttonType = Secondary
        )
    }
}

@Preview
@Composable
private fun SecondaryButtonLoadingPreview() {
    PerfectGiftTheme {
        ProjectButton(
            text = "Login",
            isLoading = true,
            buttonType = Secondary
        )
    }
}