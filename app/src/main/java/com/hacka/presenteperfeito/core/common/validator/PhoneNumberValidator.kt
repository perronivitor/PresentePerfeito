package com.hacka.presenteperfeito.core.common.validator

object PhoneNumberValidator {
    private const val PHONE_REGEX = "^\\d{11}$"

    fun isValid(value: String): Boolean {
        return value.length == 12
    }
}