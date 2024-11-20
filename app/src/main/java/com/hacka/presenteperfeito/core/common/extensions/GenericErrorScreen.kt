package com.hacka.presenteperfeito.core.common.extensions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hacka.presenteperfeito.core.designSystem.PerfectGiftTheme

@Composable
fun GenericErrorScreen(
    errorMessage: String = "Ocorreu um erro inesperado. Tente novamente mais tarde.",
    onRetry: () -> Unit,
    onBack: () -> Unit,
    errorCode: String? = null
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "Ícone de erro",
                tint = Color.Red,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            errorCode?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Código do erro: $it",
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { onRetry() },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = "Tentar Novamente")
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(
                onClick = { onBack() },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = "Voltar")
            }
        }
    }
}


@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun PreviewGenericErrorScreen() {
    PerfectGiftTheme {
        GenericErrorScreen(onRetry = {}, onBack = {})
    }
}