package com.hacka.presenteperfeito.core.designSystem.components.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.hacka.presenteperfeito.core.designSystem.PerfectGiftTheme

@Composable
fun FullScreenLoading(onUserRequestNavigationBack: (() -> Unit)? = null) {
    Dialog(onDismissRequest = onUserRequestNavigationBack ?: {}, properties = DialogProperties()) {
        Box(modifier = Modifier.wrapContentSize(Alignment.Center)) {
            CircularProgressIndicator()
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    PerfectGiftTheme {
        Scaffold { padding ->
            padding.hashCode()
            FullScreenLoading()
        }
    }
}