package com.hacka.presenteperfeito.feature.signin.data.model

import com.squareup.moshi.Json

data class LoginReqDTO(
    @Json(name = "email") val email: String, @Json(name = "password") val password: String
)
