package com.hacka.presenteperfeito.feature.signup.domain.model

import com.hacka.presenteperfeito.feature.signup.data.model.SignUpRequest

data class CreateUser(
    val email: String,
    val password: String,
    val name: String,
    val lastName: String,
) {
    fun toSignUpRequest() = SignUpRequest(
        email = email,
        password = password,
        firstName = name,
        lastName = lastName
    )
}