package org.interapidisimo.interrapidisimoapp.data.network

import org.interapidisimo.interrapidisimoapp.data.model.TableInfoModel
import retrofit2.Response
import javax.inject.Inject

class GetTablesInfoApiService @Inject constructor(
    private val getTablesInfoApiClient: GetTablesInfoApiClient
) {

    suspend fun getTablesInfo(): Response<List<TableInfoModel>> {
        return getTablesInfoApiClient.getTablesInfo()
    }
}