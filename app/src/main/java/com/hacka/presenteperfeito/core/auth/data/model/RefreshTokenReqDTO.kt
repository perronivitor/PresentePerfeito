package com.hacka.presenteperfeito.core.auth.data.model

import com.squareup.moshi.Json

data class RefreshTokenReqDTO(
    @Json(name = "refreshToken") val string: String
)