package org.interapidisimo.interrapidisimoapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.interapidisimo.interrapidisimoapp.data.database.entities.TableInfoEntity

@Dao
interface TablesInfoDAO {

    @Insert
    suspend fun insertTableInfo(tableInfoEntity: TableInfoEntity)

    @Query("SELECT * FROM database_schema_table")
    suspend fun getTablesInfo(): List<TableInfoEntity>

    @Query("SELECT COUNT(*) FROM database_schema_table")
    suspend fun getTablesInfoCount(): Int
}
