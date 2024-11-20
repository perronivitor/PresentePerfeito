package com.hacka.presenteperfeito.feature.signup.data.model

import com.squareup.moshi.Json

data class SignUpResDTO(
    @Json(name = "token") val token: String,
    @Json(name = "refreshToken") val refreshToken: String,
)