package org.interapidisimo.interrapidisimoapp.data.model

import com.google.gson.annotations.SerializedName

class LoginRequestBody(
    @SerializedName("Mac") val mac: String = "",
    @SerializedName("NomAplicacion") val applicationName: String = "",
    @SerializedName("Password") val password: String = "",
    @SerializedName("Path") val path: String = "",
    @SerializedName("Usuario") val user: String = ""
)