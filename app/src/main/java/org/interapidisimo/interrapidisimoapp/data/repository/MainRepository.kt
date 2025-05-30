package org.interapidisimo.interrapidisimoapp.data.repository

import org.interapidisimo.interrapidisimoapp.data.database.dao.*
import org.interapidisimo.interrapidisimoapp.data.database.entities.*
import org.interapidisimo.interrapidisimoapp.data.model.*
import org.interapidisimo.interrapidisimoapp.data.network.*
import org.interapidisimo.interrapidisimoapp.domain.utils.*
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val localitiesDAO: LocalitiesDAO,
    private val tablesInfoDAO: TablesInfoDAO,
    private val usersDAO: UsersDAO,
    private val getCurrentVersionApiService: GetCurrentVersionApiService,
    private val loginApiService: LoginApiService,
    private val getTablesInfoApiService: GetTablesInfoApiService,
    private val getLocalitiesApiService: GetLocalitiesApiService
) {
    suspend fun getLocalitiesCount(): Int {
        return localitiesDAO.getLocalitiesCount()
    }

    suspend fun insertLocality(localityEntity: LocalityEntity){
        localitiesDAO.insertLocality(localityEntity)
    }

    suspend fun getLocalitiesFromDB(): List<LocalityEntity>{
        return localitiesDAO.getLocalities()
    }

    suspend fun getTablesInfoCount(): Int {
        return tablesInfoDAO.getTablesInfoCount()
    }

    suspend fun insertTableInfo(tableInfoEntity: TableInfoEntity){
        tablesInfoDAO.insertTableInfo(tableInfoEntity)
    }

    suspend fun getTablesInfoFromDB(): List<TableInfoEntity>{
        return tablesInfoDAO.getTablesInfo()
    }

    suspend fun getUsersCount(): Int {
        return usersDAO.getUsersCount()
    }

    suspend fun insertUser(userEntity: UserEntity){
        usersDAO.insertUser(userEntity)
    }

    suspend fun getUsersFromDB(): List<UserEntity>{
        return usersDAO.getUsers()
    }

    suspend fun getCurrentVersion(): NetworkResult<String> {
        return HandleApi.handleGetApi {
            getCurrentVersionApiService.getCurrentVersion()
        }
    }

    suspend fun login(loginRequestBody: LoginRequestBody): NetworkResult<LoginResponse> {
        return HandleApi.handlePostApi(loginRequestBody) {
            loginApiService.login(loginRequestBody)
        }
    }

    suspend fun getTablesInfoFromServer(): NetworkResult<List<TableInfoModel>> {
        return HandleApi.handleGetApi {
            getTablesInfoApiService.getTablesInfo()
        }
    }

    suspend fun getLocalitiesFromServer(): NetworkResult<List<LocalityModel>> {
        return HandleApi.handleGetApi {
            getLocalitiesApiService.getLocalities()
        }
    }
}