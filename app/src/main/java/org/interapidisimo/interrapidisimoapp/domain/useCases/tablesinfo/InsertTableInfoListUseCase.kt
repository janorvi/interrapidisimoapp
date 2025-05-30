package org.interapidisimo.interrapidisimoapp.domain.useCases.tablesinfo

import org.interapidisimo.interrapidisimoapp.data.database.entities.TableInfoEntity
import org.interapidisimo.interrapidisimoapp.data.model.TableInfoModel
import org.interapidisimo.interrapidisimoapp.data.repository.MainRepository
import javax.inject.Inject

class InsertTableInfoListUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(tableInfoList: List<TableInfoModel>) {
        tableInfoList.forEach { tableInfoModel ->
            mainRepository.insertTableInfo(
                tableInfoEntity = TableInfoEntity(
                    id = 0,
                    tableName = tableInfoModel.tableName,
                    pk = tableInfoModel.pk,
                    creationQuery = tableInfoModel.creationQuery,
                    batchSize = tableInfoModel.batchSize,
                    filter = tableInfoModel.filter,
                    error = tableInfoModel.error,
                    fieldsNumber = tableInfoModel.fieldsNumber,
                    appMethod = tableInfoModel.appMethod,
                    updateDate = tableInfoModel.updateDate
                )
            )
        }
    }
}