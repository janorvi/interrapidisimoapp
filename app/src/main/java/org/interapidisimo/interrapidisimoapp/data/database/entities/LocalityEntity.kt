package org.interapidisimo.interrapidisimoapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "localities_table")
data class LocalityEntity(
    @PrimaryKey
    @ColumnInfo("IdLocalidad") val localityId: String = "",
    @ColumnInfo("IdTipoLocalidad") val localityTypeId: String = "",
    @ColumnInfo("IdAncestroPGrado") val ancestorPGradeId: String = "",
    @ColumnInfo("IdAncestroSGrado") val ancestorSGradeId: String = "",
    @ColumnInfo("NombreAncestroSGrado") val ancestorSGradeName: String? = null,
    @ColumnInfo("IdAncestroTGrado") val ancestorTGradeId: String? = null,
    @ColumnInfo("NombreAncestroTGrado") val ancestorTGradeName: String? = null,
    @ColumnInfo("Nombre") val name: String = "",
    @ColumnInfo("NombreCorto") val shortName: String = "",
    @ColumnInfo("NombreAncestroPGrado") val ancestorPGradeName: String = "",
    @ColumnInfo("NombreCompleto") val fullName: String = "",
    @ColumnInfo("NombreTipoLocalidad") val typeLocalityName: String? = null,
    @ColumnInfo("AsignadoEnZona") val inAreaAssigned: Boolean = false,
    @ColumnInfo("AsigandoEnZonaOrig") val inAreaOriginAssigned: Boolean = false,
    @ColumnInfo("DispoLocalidad") val availableLocality: Boolean = false,
    @ColumnInfo("NombreZona") val areaName: String? = null,
    @ColumnInfo("CodigoPostal") val zipCode: String = "",
    @ColumnInfo("Indicativo") val indicative: String? = null,
    @ColumnInfo("IdCentroServicio") val serviceCenterId: Int = 0,
    @ColumnInfo("EstadoRegistro") val registerState: Int = 0,
    @ColumnInfo("TiposLocalidades") val localitiesTypes: String? = null,
    @ColumnInfo("PermiteRecogida") val allowsCollection: Boolean = false,
    @ColumnInfo("HoraMaxRecogida") val maxHourCollection: Int = 0,
    @ColumnInfo("SeGeoreferencia") val georeferenceSe: Boolean = false,
    @ColumnInfo("ValorRecogida") val valueCollection: Double = 0.0,
    @ColumnInfo("PermitePreEnviosPunto") val allowsPreshipmentPoint: Boolean = false,
    @ColumnInfo("EtiquetaEntrega") val deliveryLabel: Boolean = false,
    @ColumnInfo("HoraMinRecogida") val minHourCollection: Int = 0,
    @ColumnInfo("AbreviacionCiudad") val abbreviationCity: String = ""
)
