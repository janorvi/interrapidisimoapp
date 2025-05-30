package org.interapidisimo.interrapidisimoapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.interapidisimo.interrapidisimoapp.data.database.entities.UserEntity

@Dao
interface UsersDAO {

    @Insert
    suspend fun insertUser(userEntity: UserEntity)

    @Query("SELECT * FROM users_table")
    suspend fun getUsers(): List<UserEntity>

    @Query("SELECT COUNT(*) FROM users_table")
    suspend fun getUsersCount(): Int
}