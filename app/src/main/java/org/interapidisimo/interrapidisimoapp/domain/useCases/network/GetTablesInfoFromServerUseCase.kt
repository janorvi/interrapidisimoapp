package org.interapidisimo.interrapidisimoapp.domain.useCases.network

import org.interapidisimo.interrapidisimoapp.data.model.NetworkResult
import org.interapidisimo.interrapidisimoapp.data.model.TableInfoModel
import org.interapidisimo.interrapidisimoapp.data.repository.MainRepository
import javax.inject.Inject

class GetTablesInfoFromServerUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): NetworkResult<List<TableInfoModel>> {
        return mainRepository.getTablesInfoFromServer()
    }
}