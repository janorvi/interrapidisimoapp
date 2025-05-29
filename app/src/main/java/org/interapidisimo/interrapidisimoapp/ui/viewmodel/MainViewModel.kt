package org.interapidisimo.interrapidisimoapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.interapidisimo.interrapidisimoapp.data.model.*
import org.interapidisimo.interrapidisimoapp.domain.useCases.GetCurrentVersionUseCase
import org.interapidisimo.interrapidisimoapp.domain.useCases.GetDatabaseSchemaUseCase
import org.interapidisimo.interrapidisimoapp.domain.useCases.GetLocalitiesUseCase
import org.interapidisimo.interrapidisimoapp.domain.useCases.LoginUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCurrentVersionUseCase: GetCurrentVersionUseCase,
    private val loginUseCase: LoginUseCase,
    private val getDatabaseSchemaUseCase: GetDatabaseSchemaUseCase,
    private val getLocalitiesUseCase: GetLocalitiesUseCase
): ViewModel() {

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
                val loginResponse: NetworkResult<LoginResponse> = loginUseCase(loginRequestBody)
                when (loginResponse) {
                    is NetworkResult.ApiSuccess -> {
                        val s = loginResponse.code
                        val t = loginResponse.data
                    }
                        //_cashPaymentResponse.postValue(paymentResponse.data)

                    is NetworkResult.ApiError ->
                        Log.e("loginError", loginResponse.message)

                    is NetworkResult.ApiException ->
                        throw (loginResponse.e)
                }
            } catch (e: Exception) {
                Log.e("loginException", e.stackTraceToString())
            }
        }
    }

    fun getDatabaseSchema(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val getDatabaseSchemaResponse: NetworkResult<List<TableInformationModel>> = getDatabaseSchemaUseCase()
                when (getDatabaseSchemaResponse) {
                    is NetworkResult.ApiSuccess -> {
                        val s = getDatabaseSchemaResponse.code
                        val t = getDatabaseSchemaResponse.data
                    }
                    //_cashPaymentResponse.postValue(paymentResponse.data)

                    is NetworkResult.ApiError ->
                        Log.e("getDatabaseSchemaError", getDatabaseSchemaResponse.message)

                    is NetworkResult.ApiException ->
                        throw (getDatabaseSchemaResponse.e)
                }
            }catch (e: Exception){
                Log.e("getDatabaseSchemaException", e.stackTraceToString())
            }
        }
    }

    fun getLocalities(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val getLocalitiesResponse: NetworkResult<List<LocalityModel>> = getLocalitiesUseCase()
                when (getLocalitiesResponse) {
                    is NetworkResult.ApiSuccess -> {
                        val s = getLocalitiesResponse.code
                        val t = getLocalitiesResponse.data
                    }
                    //_cashPaymentResponse.postValue(paymentResponse.data)

                    is NetworkResult.ApiError ->
                        Log.e("getLocalitiesError", getLocalitiesResponse.message)

                    is NetworkResult.ApiException ->
                        throw (getLocalitiesResponse.e)
                }
            }catch (e: Exception){
                Log.e("getLocalitiesException", e.stackTraceToString())
            }
        }
    }
}