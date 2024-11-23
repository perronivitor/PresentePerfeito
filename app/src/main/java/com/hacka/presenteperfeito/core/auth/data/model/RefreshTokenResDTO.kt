package com.hacka.presenteperfeito.core.auth.data.model

import com.squareup.moshi.Json

data class RefreshTokenResDTO(
    @Json(name = "token") val token: String,
    @Json(name = "refreshToken") val refreshToken: String
)