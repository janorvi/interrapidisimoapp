package org.interapidisimo.interrapidisimoapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.interapidisimo.interrapidisimoapp.data.model.LocalityModel
import org.interapidisimo.interrapidisimoapp.data.model.NetworkResult
import org.interapidisimo.interrapidisimoapp.domain.useCases.localities.*
import org.interapidisimo.interrapidisimoapp.domain.useCases.network.GetLocalitiesFromServerUseCase
import org.interapidisimo.interrapidisimoapp.ui.view.Event
import javax.inject.Inject

@HiltViewModel
class LocalitiesViewModel @Inject constructor (
    private val getLocalitiesCountUseCase: GetLocalitiesCountUseCase,
    private val insertLocalityListUseCase: InsertLocalityListUseCase,
    private val getLocalitiesFromDBUseCase: GetLocalitiesFromDBUseCase,
    private val getLocalitiesFromServerUseCase: GetLocalitiesFromServerUseCase
): ViewModel() {

    private val _message = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>> = _message

    private var _localityList = MutableLiveData<List<LocalityModel>>()
    val localityList: LiveData<List<LocalityModel>> get() = _localityList

    fun getLocalitiesFromServer(){

        viewModelScope.launch(Dispatchers.IO) {
            val localitiesCount = getLocalitiesCountUseCase()
            try {
                val getLocalitiesResponse: NetworkResult<List<LocalityModel>> = getLocalitiesFromServerUseCase()
                when (getLocalitiesResponse) {
                    is NetworkResult.ApiSuccess -> {
                        if(localitiesCount == 0){
                            insertLocalityListUseCase(getLocalitiesResponse.data)
                        }
                        _localityList.postValue(getLocalitiesResponse.data)
                    }

                    is NetworkResult.ApiError ->{
                        _message.postValue(Event("Error de conexion, cargando localidades desde la base local."))
                        delay(3000)
                        if(localitiesCount != 0){
                            _localityList.postValue(getLocalitiesFromDBUseCase())
                        }else{
                            Log.e("getLocalitiesError", "No hay localidades almacenadas en la tabla")
                            _message.postValue(Event("Error de conexion, no hay localidades almacenadas."))
                        }
                        Log.e("getLocalitiesError", getLocalitiesResponse.message)
                    }

                    is NetworkResult.ApiException -> {
                        _message.postValue(Event("Error de conexion, cargando localidades desde la base local."))
                        delay(3000)
                        if(localitiesCount != 0){
                            _localityList.postValue(getLocalitiesFromDBUseCase())
                        }else{
                            Log.e("getLocalitiesError", "No hay localidades almacenadas en la tabla")
                            _message.postValue(Event("Error de conexion, no hay localidades almacenadas."))
                        }
                        throw (getLocalitiesResponse.e)
                    }
                }
            }catch (e: Exception){
                _message.postValue(Event("Error de conexion, cargando localidades desde la base local."))
                delay(3000)
                if(localitiesCount != 0){
                    _localityList.postValue(getLocalitiesFromDBUseCase())
                }else{
                    Log.e("getLocalitiesError", "No hay localidades almacenadas en la tabla")
                    _message.postValue(Event("Error de conexion, no hay localidades almacenadas."))
                }
                Log.e("getLocalitiesException", e.stackTraceToString())
            }
        }
    }
}