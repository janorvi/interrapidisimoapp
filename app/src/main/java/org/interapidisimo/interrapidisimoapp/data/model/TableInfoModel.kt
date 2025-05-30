package org.interapidisimo.interrapidisimoapp.data.model

import com.google.gson.annotations.SerializedName

class TableInfoModel(
    @SerializedName("NombreTabla") val tableName: String = "",
    @SerializedName("Pk") val pk: String = "",
    @SerializedName("QueryCreacion") val creationQuery: String = "",
    @SerializedName("BatchSize") val batchSize: Int = 0,
    @SerializedName("Filtro") val filter: String = "",
    @SerializedName("Error") val error: String? = null,
    @SerializedName("NumeroCampos") val fieldsNumber: Int = 0,
    @SerializedName("MetodoApp") val appMethod: String? = null,
    @SerializedName("FechaActualizacionSincro") val updateDate: String = ""
)

