package com.hacka.presenteperfeito.feature.signup.presentation.model

import com.hacka.presenteperfeito.feature.signup.data.model.SignUpRequest

data class CreateUser(
    val email: String,
    val password: String,
    val name: String,
    val lastName: String,
) {
    fun toSignUpReqDTO() = SignUpRequest(
        email = email,
        password = password,
        firstName = name,
        lastName = lastName
    )
}