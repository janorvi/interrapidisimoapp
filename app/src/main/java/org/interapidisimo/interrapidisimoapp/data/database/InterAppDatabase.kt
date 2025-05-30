package org.interapidisimo.interrapidisimoapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.interapidisimo.interrapidisimoapp.data.database.dao.*
import org.interapidisimo.interrapidisimoapp.data.database.entities.*

@Database(
    entities = [LocalityEntity::class, TableInfoEntity::class, UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class InterAppDatabase: RoomDatabase() {

    abstract fun getLocalitiesDAO(): LocalitiesDAO
    abstract fun getTablesInfoDAO(): TablesInfoDAO
    abstract fun getUsersDAO(): UsersDAO
}