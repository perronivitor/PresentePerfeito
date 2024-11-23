package com.hacka.presenteperfeito.feature.signup.domain.model

import com.hacka.presenteperfeito.feature.signup.data.model.SignUpRequestDTO

data class CreateUser(
    val email: String,
    val password: String,
    val name: String,
    val lastName: String,
) {
    fun toSignUpRequest() = SignUpRequestDTO(
        email = email,
        password = password,
        firstName = name,
        lastName = lastName
    )
}