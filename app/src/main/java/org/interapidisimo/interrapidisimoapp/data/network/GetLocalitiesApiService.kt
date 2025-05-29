package org.interapidisimo.interrapidisimoapp.data.network

import org.interapidisimo.interrapidisimoapp.data.model.LocalityModel
import retrofit2.Response
import javax.inject.Inject

class GetLocalitiesApiService @Inject constructor(
    private val getLocalitiesApiClient: GetLocalitiesApiClient
) {

    suspend fun getLocalities(): Response<List<LocalityModel>> {
        return getLocalitiesApiClient.getLocalities()
    }
}