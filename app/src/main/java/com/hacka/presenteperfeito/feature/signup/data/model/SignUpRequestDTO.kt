package com.hacka.presenteperfeito.feature.signup.data.model

import com.squareup.moshi.Json

data class SignUpRequestDTO(
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String,
    @Json(name = "firstName") val firstName: String,
    @Json(name = "lastName") val lastName: String,
)