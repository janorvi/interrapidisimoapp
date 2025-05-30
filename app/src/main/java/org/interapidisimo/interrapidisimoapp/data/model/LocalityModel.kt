package org.interapidisimo.interrapidisimoapp.data.model

import com.google.gson.annotations.SerializedName

data class LocalityModel(
    @SerializedName("IdLocalidad") val localityId: String = "",
    @SerializedName("IdTipoLocalidad") val localityTypeId: String = "",
    @SerializedName("IdAncestroPGrado") val ancestorPGradeId: String = "",
    @SerializedName("IdAncestroSGrado") val ancestorSGradeId: String = "",
    @SerializedName("NombreAncestroSGrado") val ancestorSGradeName: String? = null,
    @SerializedName("IdAncestroTGrado") val ancestorTGradeId: String? = null,
    @SerializedName("NombreAncestroTGrado") val ancestorTGradeName: String? = null,
    @SerializedName("Nombre") val name: String = "",
    @SerializedName("NombreCorto") val shortName: String = "",
    @SerializedName("NombreAncestroPGrado") val ancestorPGradeName: String = "",
    @SerializedName("NombreCompleto") val fullName: String = "",
    @SerializedName("NombreTipoLocalidad") val typeLocalityName: String? = null,
    @SerializedName("AsignadoEnZona") val inAreaAssigned: Boolean = false,
    @SerializedName("AsigandoEnZonaOrig") val inAreaOriginAssigned: Boolean = false,
    @SerializedName("DispoLocalidad") val availableLocality: Boolean = false,
    @SerializedName("NombreZona") val areaName: String? = null,
    @SerializedName("CodigoPostal") val zipCode: String = "",
    @SerializedName("Indicativo") val indicative: String? = null,
    @SerializedName("IdCentroServicio") val serviceCenterId: Int = 0,
    @SerializedName("EstadoRegistro") val registerState: Int = 0,
    @SerializedName("TiposLocalidades") val localitiesTypes: String? = null,
    @SerializedName("PermiteRecogida") val allowsCollection: Boolean = false,
    @SerializedName("HoraMaxRecogida") val maxHourCollection: Int = 0,
    @SerializedName("SeGeoreferencia") val georeferenceSe: Boolean = false,
    @SerializedName("ValorRecogida") val valueCollection: Double = 0.0,
    @SerializedName("PermitePreEnviosPunto") val allowsPreshipmentPoint: Boolean = false,
    @SerializedName("EtiquetaEntrega") val deliveryLabel: Boolean = false,
    @SerializedName("HoraMinRecogida") val minHourCollection: Int = 0,
    @SerializedName("AbreviacionCiudad") val abbreviationCity: String = ""
)
