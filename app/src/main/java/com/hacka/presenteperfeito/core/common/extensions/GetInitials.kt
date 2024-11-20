package com.hacka.presenteperfeito.core.common.extensions

fun getInitials(userName: String): String {
    return userName
        .split(" ")
        .mapNotNull { it.firstOrNull()?.toString() }
        .joinToString("")
}