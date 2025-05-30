package org.interapidisimo.interrapidisimoapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "database_schema_table")
data class TableInfoEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("ID") val id: Int = 0,
    @ColumnInfo("NombreTabla") val tableName: String = "",
    @ColumnInfo("Pk") val pk: String = "",
    @ColumnInfo("QueryCreacion") val creationQuery: String = "",
    @ColumnInfo("BatchSize") val batchSize: Int = 0,
    @ColumnInfo("Filtro") val filter: String = "",
    @ColumnInfo("Error") val error: String? = null,
    @ColumnInfo("NumeroCampos") val fieldsNumber: Int = 0,
    @ColumnInfo("MetodoApp") val appMethod: String? = null,
    @ColumnInfo("FechaActualizacionSincro") val updateDate: String = ""
)