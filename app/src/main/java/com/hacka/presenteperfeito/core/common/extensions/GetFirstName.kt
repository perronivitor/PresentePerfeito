package com.hacka.presenteperfeito.core.common.extensions

fun getFirstName(fullName: String): String {
    return fullName.split(" ").firstOrNull() ?: ""
}