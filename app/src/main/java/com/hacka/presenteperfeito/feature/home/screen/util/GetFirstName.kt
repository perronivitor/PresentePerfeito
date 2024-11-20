package com.hacka.presenteperfeito.feature.home.screen.util

fun getFirstName(fullName: String): String {
    return fullName.split(" ").firstOrNull() ?: ""
}