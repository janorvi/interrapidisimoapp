package org.interapidisimo.interrapidisimoapp.data.repository

import org.interapidisimo.interrapidisimoapp.data.model.*
import org.interapidisimo.interrapidisimoapp.data.network.*
import org.interapidisimo.interrapidisimoapp.domain.utils.*
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val getCurrentVersionApiService: GetCurrentVersionApiService,
    private val loginApiService: LoginApiService,
    private val getDatabaseSchemaApiService: GetDatabaseSchemaApiService,
    private val getLocalitiesApiService: GetLocalitiesApiService
) {

    suspend fun getCurrentVersion(): NetworkResult<String> {
        return HandleApi.handleGetApi {
            getCurrentVersionApiService.getCurrentVersion()
        }
    }

    suspend fun login(loginRequestBody: LoginRequestBody): NetworkResult<LoginResponse> {
        return HandleApi.handlePostApi(loginRequestBody) {
            loginApiService.login(loginRequestBody)
        }
    }

    suspend fun getDatabaseSchema(): NetworkResult<List<TableInformationModel>> {
        return HandleApi.handleGetApi {
            getDatabaseSchemaApiService.getDatabaseSchema()
        }
    }

    suspend fun getLocalities(): NetworkResult<List<LocalityModel>> {
        return HandleApi.handleGetApi {
            getLocalitiesApiService.getLocalities()
        }
    }
}