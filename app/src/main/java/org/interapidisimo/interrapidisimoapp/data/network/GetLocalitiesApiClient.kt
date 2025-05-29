package org.interapidisimo.interrapidisimoapp.data.network

import org.interapidisimo.interrapidisimoapp.data.model.LocalityModel
import retrofit2.Response
import retrofit2.http.GET

interface GetLocalitiesApiClient {
    @GET("ParametrosFramework/ObtenerLocalidadesRecogidas")
    suspend fun getLocalities(): Response<List<LocalityModel>>
}