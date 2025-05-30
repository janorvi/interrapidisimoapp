package org.interapidisimo.interrapidisimoapp.domain.useCases.localities

import org.interapidisimo.interrapidisimoapp.data.repository.MainRepository
import javax.inject.Inject

class GetLocalitiesCountUseCase @Inject constructor(
private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): Int {
        return mainRepository.getLocalitiesCount()
    }
}