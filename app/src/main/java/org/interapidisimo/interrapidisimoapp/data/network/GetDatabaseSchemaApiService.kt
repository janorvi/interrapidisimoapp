package org.interapidisimo.interrapidisimoapp.data.network

import org.interapidisimo.interrapidisimoapp.data.model.TableInformationModel
import retrofit2.Response
import javax.inject.Inject

class GetDatabaseSchemaApiService @Inject constructor(
    private val getDatabaseSchemaApiClient: GetDatabaseSchemaApiClient
) {

    suspend fun getDatabaseSchema(): Response<List<TableInformationModel>> {
        return getDatabaseSchemaApiClient.getDatabaseSchema()
    }
}