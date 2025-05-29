package org.interapidisimo.interrapidisimoapp.data.network

import org.interapidisimo.interrapidisimoapp.data.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApiClient {
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
    @POST("Seguridad/AuthenticaUsuarioApp")
    suspend fun login(
        @Body loginRequestBody: LoginRequestBody
    ): Response<LoginResponse>
}