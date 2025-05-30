package org.interapidisimo.interrapidisimoapp.domain.useCases.network

import org.interapidisimo.interrapidisimoapp.data.model.NetworkResult
import org.interapidisimo.interrapidisimoapp.data.repository.MainRepository
import javax.inject.Inject

class GetCurrentVersionUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): NetworkResult<String> {
        return mainRepository.getCurrentVersion()
    }
}