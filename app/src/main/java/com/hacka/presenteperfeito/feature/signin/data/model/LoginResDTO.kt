package com.hacka.presenteperfeito.feature.signin.data.model

import com.squareup.moshi.Json

data class LoginResDTO(
    @Json(name = "token") val token: String, @Json(name = "refreshToken") val refreshToken: String
)