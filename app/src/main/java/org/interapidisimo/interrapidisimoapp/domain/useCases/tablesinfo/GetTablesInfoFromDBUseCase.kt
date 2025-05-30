package org.interapidisimo.interrapidisimoapp.domain.useCases.tablesinfo

import org.interapidisimo.interrapidisimoapp.data.database.entities.TableInfoEntity
import org.interapidisimo.interrapidisimoapp.data.repository.MainRepository
import javax.inject.Inject

class GetTablesInfoFromDBUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): List<TableInfoEntity> {
        return mainRepository.getTablesInfoFromDB()
    }
}