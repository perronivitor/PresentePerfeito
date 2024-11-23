package com.hacka.presenteperfeito.core.designSystem.components.textFields

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hacka.presenteperfeito.R
import com.hacka.presenteperfeito.core.designSystem.PerfectGiftTheme

@Composable
fun PrimaryTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    leftIcon: @Composable() (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    errorMessage: String? = null,
    placeHolder: String = "",
    imeAction: ImeAction = ImeAction.Next,
    isPassword: Boolean = false
) {
    var passwordVisible by remember {

        mutableStateOf(false)
    }
    Column(modifier = modifier) {
        OutlinedTextField(
            colors = OutlinedTextFieldDefaults.colors()
                .copy(
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                    focusedContainerColor = MaterialTheme.colorScheme.background
                ),
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = placeHolder)
            },

            visualTransformation = if (passwordVisible || !isPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisible = !passwordVisible
                }) {
                    if (isPassword) if (passwordVisible) {
                        Icon(
                            tint = MaterialTheme.colorScheme.primary,
                            painter = painterResource(id = R.drawable.ic_visibility),
                            contentDescription = ""
                        )
                    } else {
                        Icon(
                            tint = MaterialTheme.colorScheme.primary,
                            painter = painterResource(id = R.drawable.ic_visibility_off),
                            contentDescription = ""
                        )

                    }
                }
            },
            leadingIcon = {
                if (leftIcon != null) leftIcon()
            },
            shape = RoundedCornerShape(50.dp),
            value = value,
            onValueChange = onValueChange
        )
        Spacer(modifier = Modifier.height(8.dp))
        AnimatedVisibility(visible = errorMessage != null) {
            Text(text = errorMessage ?: "")

        }
    }

}

@Composable
@Preview(showBackground = true)
private fun PrimarytextFieldPreview() {
    PerfectGiftTheme {
        PrimaryTextField(value = "Primary Text Field", onValueChange = {}, leftIcon = {
            androidx.compose.material3.Icon(
                imageVector = Icons.AutoMirrored.Filled.Send, contentDescription = ""
            )
        }, isPassword = true)
    }

}