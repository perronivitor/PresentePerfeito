package com.hacka.presenteperfeito.core.network.interceptor

import com.hacka.presenteperfeito.core.auth.data.repository.AuthRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.io.IOException

/**
 * @throws[AuthenticationException] - Error occurred during authentication
 * @throws[ExpiredTokenException] - Token has expired. Send user back to login
 */
class SessionAuthenticator(private val authRepository: AuthRepository) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val request = response.request
        if (request.header("Authorization").isNullOrBlank()) return null

        val newToken = try {
            runBlocking { authRepository.refreshCurrentToken() }.token
        } catch (err: ExpiredTokenException) {
            // The token has expired, we need to logout.
            runBlocking {
                authRepository.logout()
            }
            null
        } catch (err: Throwable) {
            // Another error occurred, we need to suppress it inside an IOException to avoid
            // a fatal crash.
            if (err !is IOException) throw AuthenticationException(
                cause = err, message = err.message
            )
            else throw err
        }

        return if (newToken.isNullOrBlank()) null
        else request.newBuilder().header("Authorization", "Bearer $newToken").build()
    }
}

class AuthenticationException(
    override val message: String?, override val cause: Throwable? = null
) : IOException()

class ExpiredTokenException(override val message: String? = null) : Exception()