package com.hacka.presenteperfeito.core.network.di

import com.hacka.presenteperfeito.feature.signin.data.service.SignInService
import org.koin.core.annotation.Single

@Single
fun signInService(): SignInService = retrofit().create(SignInService::class.java)