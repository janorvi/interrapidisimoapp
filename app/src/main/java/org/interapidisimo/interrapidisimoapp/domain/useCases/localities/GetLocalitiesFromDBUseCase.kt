package org.interapidisimo.interrapidisimoapp.domain.useCases.localities

import org.interapidisimo.interrapidisimoapp.data.database.entities.LocalityEntity
import org.interapidisimo.interrapidisimoapp.data.repository.MainRepository
import javax.inject.Inject

class GetLocalitiesFromDBUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): List<LocalityEntity> {
        return mainRepository.getLocalitiesFromDB()
    }
}