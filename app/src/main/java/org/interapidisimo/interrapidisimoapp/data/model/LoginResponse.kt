package org.interapidisimo.interrapidisimoapp.data.model

import com.google.gson.annotations.SerializedName

class LoginResponse(
    @SerializedName("Usuario") val user: String = "",
    @SerializedName("Identificacion") val identification: String = "",
    @SerializedName("Nombre") val name: String = "",
)

