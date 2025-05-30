package org.interapidisimo.interrapidisimoapp.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.interapidisimo.interrapidisimoapp.data.database.InterAppDatabase
import org.interapidisimo.interrapidisimoapp.data.database.dao.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val INTERAPP_DATABASE = "interappDB"

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context): InterAppDatabase =
        Room
            .databaseBuilder(context, InterAppDatabase::class.java, INTERAPP_DATABASE)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideLocalitiesDAO(db: InterAppDatabase): LocalitiesDAO = db.getLocalitiesDAO()

    @Singleton
    @Provides
    fun provideTablesInfoDAO(db: InterAppDatabase): TablesInfoDAO = db.getTablesInfoDAO()

    @Singleton
    @Provides
    fun provideUsersDAO(db: InterAppDatabase): UsersDAO = db.getUsersDAO()
}