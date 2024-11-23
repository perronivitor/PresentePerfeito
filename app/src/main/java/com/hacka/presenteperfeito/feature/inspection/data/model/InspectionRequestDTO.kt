package com.hacka.presenteperfeito.feature.inspection.data.model

import com.squareup.moshi.Json

data class InspectionRequestDTO(
    @Json(name = "name") val name: String,
    @Json(name = "relationShip") val relationShip: String,
    @Json(name = "occasion") val occasion: String,
    @Json(name = "whatsappPhoneNumber") val whatsappPhoneNumber: String,
)