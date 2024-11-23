package com.hacka.presenteperfeito.core.network.di

import com.hacka.presenteperfeito.core.auth.data.service.AuthService
import com.hacka.presenteperfeito.feature.inspection.data.service.InspectionService
import com.hacka.presenteperfeito.feature.signin.data.service.SignInService
import com.hacka.presenteperfeito.feature.signup.data.service.SignUpService
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import retrofit2.Retrofit

@Single
fun signInService(@Named(ANONYMOUS_RETROFIT) retrofit: Retrofit): SignInService =
    retrofit.create(SignInService::class.java)

@Single
fun signUpService(@Named(ANONYMOUS_RETROFIT) retrofit: Retrofit): SignUpService =
    retrofit.create(SignUpService::class.java)

@Single
fun authService(@Named(ANONYMOUS_RETROFIT) retrofit: Retrofit): AuthService =
    retrofit.create(AuthService::class.java)

@Single
fun inspectionService(@Named(ANONYMOUS_RETROFIT) retrofit: Retrofit): InspectionService =
    retrofit().create(InspectionService::class.java)