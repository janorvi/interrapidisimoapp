package org.interapidisimo.interrapidisimoapp.domain.useCases

import org.interapidisimo.interrapidisimoapp.data.model.LocalityModel
import org.interapidisimo.interrapidisimoapp.data.model.NetworkResult
import org.interapidisimo.interrapidisimoapp.data.repository.MainRepository
import javax.inject.Inject

class GetLocalitiesUseCase@Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): NetworkResult<List<LocalityModel>> {
        return mainRepository.getLocalities()
    }
}