package com.hacka.presenteperfeito.core.designSystem.components.textAnnotated

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.hacka.presenteperfeito.core.designSystem.PerfectGiftTheme

@Composable
fun AnnotatedString(
    startText: String,
    startTextColor: Color,
    underlineText: String,
    underlineTextColor: Color,
    modifier: Modifier = Modifier,
    onUnderlineClick: () -> Unit,
) {
    val fullText = "$startText $underlineText"

    val annotatedString = buildAnnotatedString {
        val underlineTextStartIndex = fullText.indexOf(underlineText)
        val underlineTextEndIndex = underlineTextStartIndex + underlineText.length

        append(fullText)

        addStyle(
            style = SpanStyle(color = startTextColor),
            start = 0,
            end = underlineTextEndIndex
        )

        addLink(
            clickable = LinkAnnotation.Clickable(
                tag = "registerText",
                styles = TextLinkStyles(
                    style = SpanStyle(
                        color = underlineTextColor,
                        textDecoration = TextDecoration.Underline
                    )
                ),
                linkInteractionListener = {
                    onUnderlineClick.invoke()
                }
            ),
            start = underlineTextStartIndex,
            end = underlineTextEndIndex,
        )
    }

    Text(
        text = annotatedString,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

@Preview
@Composable
private fun AnnotatedStringPreview() {
    PerfectGiftTheme {
        AnnotatedString(
            startText = "Já tem uma conta?",
            startTextColor = Color.Black,
            underlineText = "Faça login",
            underlineTextColor = Color.Blue,
            onUnderlineClick = {}
        )
    }
}