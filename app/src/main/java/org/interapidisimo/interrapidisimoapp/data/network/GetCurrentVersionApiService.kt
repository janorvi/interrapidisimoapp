package org.interapidisimo.interrapidisimoapp.data.network

import retrofit2.Response
import javax.inject.Inject

class GetCurrentVersionApiService @Inject constructor(
    private val getCurrentVersionApiClient: GetCurrentVersionApiClient
) {

    suspend fun getCurrentVersion(): Response<String> {
        return getCurrentVersionApiClient.getCurrentVersion()
    }
}