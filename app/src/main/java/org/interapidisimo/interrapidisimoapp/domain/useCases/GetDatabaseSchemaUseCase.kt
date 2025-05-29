package org.interapidisimo.interrapidisimoapp.domain.useCases

import org.interapidisimo.interrapidisimoapp.data.model.NetworkResult
import org.interapidisimo.interrapidisimoapp.data.model.TableInformationModel
import org.interapidisimo.interrapidisimoapp.data.repository.MainRepository
import javax.inject.Inject

class GetDatabaseSchemaUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): NetworkResult<List<TableInformationModel>> {
        return mainRepository.getDatabaseSchema()
    }
}