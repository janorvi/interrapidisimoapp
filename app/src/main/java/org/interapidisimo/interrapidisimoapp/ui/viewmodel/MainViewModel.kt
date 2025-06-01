package org.interapidisimo.interrapidisimoapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.interapidisimo.interrapidisimoapp.data.model.*
import org.interapidisimo.interrapidisimoapp.domain.useCases.network.*
import org.interapidisimo.interrapidisimoapp.domain.useCases.localities.*
import org.interapidisimo.interrapidisimoapp.domain.useCases.tablesinfo.*
import org.interapidisimo.interrapidisimoapp.domain.useCases.users.GetUsersCountUseCase
import org.interapidisimo.interrapidisimoapp.domain.useCases.users.GetUsersFromDBUseCase
import org.interapidisimo.interrapidisimoapp.domain.useCases.users.InsertUserUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLocalitiesCountUseCase: GetLocalitiesCountUseCase,
    private val insertLocalityListUseCase: InsertLocalityListUseCase,
    private val getLocalitiesFromDBUseCase: GetLocalitiesFromDBUseCase,
    private val getTablesInfoCountUseCase: GetTablesInfoCountUseCase,
    private val insertTableInfoListUseCase: InsertTableInfoListUseCase,
    private val getTablesInfoFromDBUseCase: GetTablesInfoFromDBUseCase,
    private val getUsersCountUseCase: GetUsersCountUseCase,
    private val insertUserUseCase: InsertUserUseCase,
    private val getUsersFromDBUseCase: GetUsersFromDBUseCase,
    private val getCurrentVersionUseCase: GetCurrentVersionUseCase,
    private val loginUseCase: LoginUseCase,
    private val getTablesInfoFromServerUseCase: GetTablesInfoFromServerUseCase,
    private val getLocalitiesFromServerUseCase: GetLocalitiesFromServerUseCase
): ViewModel() {

    private var _tableInfoList = MutableLiveData<List<TableInfoModel>>()
    val tableInfoList: LiveData<List<TableInfoModel>> get() = _tableInfoList

    private var _localityList = MutableLiveData<List<LocalityModel>>()
    val localityList: LiveData<List<LocalityModel>> get() = _localityList

    fun getCurrentVersion(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val getCurrentVersionResponse: NetworkResult<String> = getCurrentVersionUseCase()
                when (getCurrentVersionResponse) {
                    is NetworkResult.ApiSuccess -> {
                        val s = getCurrentVersionResponse.code
                        val t = getCurrentVersionResponse.data
                    }
                    //_cashPaymentResponse.postValue(paymentResponse.data)

                    is NetworkResult.ApiError ->
                        Log.e("getCurrentVersionError", getCurrentVersionResponse.message)

                    is NetworkResult.ApiException ->
                        throw (getCurrentVersionResponse.e)
                }
            }catch (e: Exception){
                Log.e("getCurrentVersionException", e.stackTraceToString())
            }
        }
    }

    fun login(loginRequestBody: LoginRequestBody) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val usersCount = getUsersCountUseCase()
                val loginResponse: NetworkResult<LoginResponse> = loginUseCase(loginRequestBody)
                when (loginResponse) {
                    is NetworkResult.ApiSuccess -> {
                        if(usersCount == 0){
                            insertUserUseCase(loginResponse.data)
                        }
                    }

                    is NetworkResult.ApiError -> {
                        if(usersCount != 0){
                            getUsersFromDBUseCase()
                        }else{
                            Log.e("loginError", "No hay usuarios almacenados en la tabla")
                        }
                        Log.e("loginError", loginResponse.message)
                    }


                    is NetworkResult.ApiException ->{
                        if(usersCount != 0){
                            val s = getUsersFromDBUseCase()
                            val t = ""
                        }else{
                            Log.e("loginError", "No hay usuarios almacenados en la tabla")
                        }
                        throw (loginResponse.e)
                    }

                }
            } catch (e: Exception) {
                Log.e("loginException", e.stackTraceToString())
            }
        }
    }

    fun getTablesInfoFromServer(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val tablesInfoCount = getTablesInfoCountUseCase()
                val getTablesInfoResponse: NetworkResult<List<TableInfoModel>> = getTablesInfoFromServerUseCase()
                when (getTablesInfoResponse) {
                    is NetworkResult.ApiSuccess -> {
                        if(tablesInfoCount == 0){
                            insertTableInfoListUseCase(getTablesInfoResponse.data)
                        }
                        _tableInfoList.postValue(getTablesInfoResponse.data)
                    }

                    is NetworkResult.ApiError ->{
                        if(tablesInfoCount != 0){
                            getTablesInfoFromDBUseCase()
                        }else{
                            Log.e("getLocalitiesError", "No hay localidades almacenadas en la tabla")
                        }
                        Log.e("getTablesInfoError", getTablesInfoResponse.message)
                    }

                    is NetworkResult.ApiException ->{
                        if(tablesInfoCount != 0){
                            getTablesInfoFromDBUseCase()
                        }else{
                            Log.e("getLocalitiesError", "No hay localidades almacenadas en la tabla")
                        }
                        throw (getTablesInfoResponse.e)
                    }
                }
            }catch (e: Exception){
                Log.e("getTablesInfoException", e.stackTraceToString())
            }
        }
    }

    fun getLocalitiesFromServer(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val localitiesCount = getLocalitiesCountUseCase()
                val getLocalitiesResponse: NetworkResult<List<LocalityModel>> = getLocalitiesFromServerUseCase()
                when (getLocalitiesResponse) {
                    is NetworkResult.ApiSuccess -> {
                        if(localitiesCount == 0){
                            insertLocalityListUseCase(getLocalitiesResponse.data)
                        }
                        _localityList.postValue(getLocalitiesResponse.data)
                    }

                    is NetworkResult.ApiError ->{
                        if(localitiesCount != 0){
                            getLocalitiesFromDBUseCase()
                        }else{
                            Log.e("getLocalitiesError", "No hay localidades almacenadas en la tabla")
                        }
                        Log.e("getLocalitiesError", getLocalitiesResponse.message)
                    }

                    is NetworkResult.ApiException -> {
                        if(localitiesCount != 0){
                            getLocalitiesFromDBUseCase()
                        }else{
                            Log.e("getLocalitiesError", "No hay localidades almacenadas en la tabla")
                        }
                        throw (getLocalitiesResponse.e)
                    }
                }
            }catch (e: Exception){
                Log.e("getLocalitiesException", e.stackTraceToString())
            }
        }
    }
}