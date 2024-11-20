package com.hacka.presenteperfeito.feature.signup.presentation.uiState

import android.net.Uri
import androidx.annotation.IntegerRes
import com.hacka.presenteperfeito.core.common.UiState

data class SignUpFormState(
    val profilePictureUri: Uri? = null,
    val firstName: String = "",
    @IntegerRes
    val firstNameError: Int? = null,
    val lastName: String = "",
    @IntegerRes
    val lastNameError: Int? = null,
    val email: String = "",
    @IntegerRes
    val emailError: Int? = null,
    val password: String = "",
    @IntegerRes
    val passwordError: Int? = null,
    val passwordConfirmation: String = "",
    @IntegerRes
    val passwordConfirmationError: Int? = null,
    @IntegerRes
    val passwordExtraTextId: Int? = null,
    val isProfilePictureModalBottomSheetOpen: Boolean = false,
    val hasError: Boolean = false,
    val isLoading: Boolean = false,
    val event: SignUpFormEvent? = null
) : UiState