package org.interapidisimo.interrapidisimoapp.domain.useCases.users

import org.interapidisimo.interrapidisimoapp.data.repository.MainRepository
import javax.inject.Inject

class GetUsersCountUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): Int {
        return mainRepository.getUsersCount()
    }
}