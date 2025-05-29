package org.interapidisimo.interrapidisimoapp.data.model

import com.google.gson.annotations.SerializedName

class TableInformationModel(
    @SerializedName("NombreTabla") val tableName: String = "",
    @SerializedName("Pk") val pk: String = "",
    @SerializedName("QueryCreacion") val creationQuery: String = "",
    @SerializedName("BatchSize") val batchSize: Int = 0,
    @SerializedName("Filtro") val filter: String = "",
    @SerializedName("Error") val error: String = "",
    @SerializedName("NumeroCampos") val fieldsNumber: Int = 0,
    @SerializedName("MetodoApp") val appMethod: String = "",
    @SerializedName("FechaActulizacionSincro") val updateDate: String = ""
)

