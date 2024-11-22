package com.hacka.presenteperfeito.core.network.di

import com.hacka.presenteperfeito.feature.signin.data.service.SignInService
import com.hacka.presenteperfeito.feature.signup.data.service.SignUpService
import org.koin.core.annotation.Single

@Single
fun signInService(): SignInService = retrofit().create(SignInService::class.java)

@Single
fun signUpService(): SignUpService = retrofit().create(SignUpService::class.java)