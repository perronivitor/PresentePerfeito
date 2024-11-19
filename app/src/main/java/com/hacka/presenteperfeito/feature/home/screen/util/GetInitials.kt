package com.hacka.presenteperfeito.feature.home.screen.util

fun getInitials(userName: String): String {
    return userName
        .split(" ")
        .mapNotNull { it.firstOrNull()?.toString() }
        .joinToString("")
}