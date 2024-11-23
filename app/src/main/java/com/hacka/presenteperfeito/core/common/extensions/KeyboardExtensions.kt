package com.hacka.presenteperfeito.core.common.extensions

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

fun KeyboardType.getVisualTransformationForPassword(visibility: Boolean): VisualTransformation {
    return if (this == KeyboardType.Password && !visibility) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }
}