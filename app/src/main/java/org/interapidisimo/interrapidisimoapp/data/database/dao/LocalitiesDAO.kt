package org.interapidisimo.interrapidisimoapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.interapidisimo.interrapidisimoapp.data.database.entities.LocalityEntity

@Dao
interface LocalitiesDAO {

    @Insert
    suspend fun insertLocality(localityEntity: LocalityEntity)

    @Query("SELECT * FROM localities_table")
    suspend fun getLocalities(): List<LocalityEntity>

    @Query("SELECT COUNT(*) FROM localities_table")
    suspend fun getLocalitiesCount(): Int
}