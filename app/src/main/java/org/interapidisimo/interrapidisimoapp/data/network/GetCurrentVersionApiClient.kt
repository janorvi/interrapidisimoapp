package org.interapidisimo.interrapidisimoapp.data.network

import retrofit2.Response
import retrofit2.http.GET

interface GetCurrentVersionApiClient {
    @GET("ParametrosFramework/ConsultarParametrosFramework/VPStoreAppControl")
    suspend fun getCurrentVersion(): Response<String>
}