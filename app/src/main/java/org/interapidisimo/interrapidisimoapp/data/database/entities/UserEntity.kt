package org.interapidisimo.interrapidisimoapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo("Nombre") val name: String = "",
    @ColumnInfo("Usuario") val user: String = "",
    @ColumnInfo("Identificacion") val identification: String = "",
)
