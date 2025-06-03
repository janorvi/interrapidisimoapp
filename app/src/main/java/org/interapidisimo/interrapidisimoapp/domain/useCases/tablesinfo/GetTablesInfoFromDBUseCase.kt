package org.interapidisimo.interrapidisimoapp.domain.useCases.tablesinfo

import org.interapidisimo.interrapidisimoapp.data.model.TableInfoModel
import org.interapidisimo.interrapidisimoapp.data.repository.MainRepository
import javax.inject.Inject

class GetTablesInfoFromDBUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): List<TableInfoModel> {
        var tableInfoList: ArrayList<TableInfoModel> = arrayListOf()
        mainRepository.getTablesInfoFromDB().forEach { tableInfoEntity ->
            tableInfoList.add(
                TableInfoModel(
                    tableName = tableInfoEntity.tableName,
                    pk = tableInfoEntity.pk,
                    creationQuery = tableInfoEntity.creationQuery,
                    batchSize = tableInfoEntity.batchSize,
                    filter = tableInfoEntity.filter,
                    error = tableInfoEntity.error,
                    fieldsNumber = tableInfoEntity.fieldsNumber,
                    appMethod = tableInfoEntity.appMethod,
                    updateDate = tableInfoEntity.updateDate
                )
            )
        }
        return tableInfoList
    }
}