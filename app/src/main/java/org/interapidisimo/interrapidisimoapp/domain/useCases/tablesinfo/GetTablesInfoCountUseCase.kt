package org.interapidisimo.interrapidisimoapp.domain.useCases.tablesinfo

import org.interapidisimo.interrapidisimoapp.data.repository.MainRepository
import javax.inject.Inject

class GetTablesInfoCountUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): Int {
        return mainRepository.getTablesInfoCount()
    }
}