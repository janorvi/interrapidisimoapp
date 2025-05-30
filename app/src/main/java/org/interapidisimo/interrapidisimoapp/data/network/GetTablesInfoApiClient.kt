package org.interapidisimo.interrapidisimoapp.data.network

import org.interapidisimo.interrapidisimoapp.data.model.TableInfoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface GetTablesInfoApiClient {
    @Headers(
        "Usuario:pam.meredy21",
        "Identificacion:987204545",
        "Accept:text/json",
        "IdUsuario:pam.meredy21",
        "IdCentroServicio:1295",
        "NombreCentroServicio:PTO/BOGOTA/CUND/COL/OF PRINCIPAL - CRA 30 # 7-45",
        "IdAplicativoOrigen:9",
        "Content-Type:application/json"
    )
    @GET("SincronizadorDatos/ObtenerEsquema/true")
    suspend fun getTablesInfo(): Response<List<TableInfoModel>>
}