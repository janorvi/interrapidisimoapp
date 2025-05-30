package org.interapidisimo.interrapidisimoapp.data.network

import org.interapidisimo.interrapidisimoapp.data.model.*
import retrofit2.Response
import javax.inject.Inject

class LoginApiService @Inject constructor(
    private val loginApiClient: LoginApiClient
) {

    suspend fun login(loginRequestBody: LoginRequestBody): Response<LoginResponse> {
        return loginApiClient.login(loginRequestBody)
    }
}