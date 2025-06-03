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
import org.interapidisimo.interrapidisimoapp.data.model.NetworkResult
import org.interapidisimo.interrapidisimoapp.data.model.TableInfoModel
import org.interapidisimo.interrapidisimoapp.domain.useCases.network.GetTablesInfoFromServerUseCase
import org.interapidisimo.interrapidisimoapp.domain.useCases.tablesinfo.*
import org.interapidisimo.interrapidisimoapp.ui.view.Event
import javax.inject.Inject

@HiltViewModel
class TablesInfoViewModel @Inject constructor (
    private val getTablesInfoCountUseCase: GetTablesInfoCountUseCase,
    private val insertTableInfoListUseCase: InsertTableInfoListUseCase,
    private val getTablesInfoFromDBUseCase: GetTablesInfoFromDBUseCase,
    private val getTablesInfoFromServerUseCase: GetTablesInfoFromServerUseCase,
): ViewModel() {

    private val _message = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>> = _message

    private var _tableInfoList = MutableLiveData<List<TableInfoModel>>()
    val tableInfoList: LiveData<List<TableInfoModel>> get() = _tableInfoList

    fun getTablesInfoFromServer(){
        viewModelScope.launch(Dispatchers.IO) {
            val tablesInfoCount = getTablesInfoCountUseCase()
            try {
                val getTablesInfoResponse: NetworkResult<List<TableInfoModel>> = getTablesInfoFromServerUseCase()
                when (getTablesInfoResponse) {
                    is NetworkResult.ApiSuccess -> {
                        if(tablesInfoCount == 0){
                            insertTableInfoListUseCase(getTablesInfoResponse.data)
                        }
                        _tableInfoList.postValue(getTablesInfoResponse.data)
                    }

                    is NetworkResult.ApiError ->{
                        _message.postValue(Event("Error de conexion, cargando tablas  desde la base local."))
                        delay(3000)
                        if(tablesInfoCount != 0){
                            _tableInfoList.postValue(getTablesInfoFromDBUseCase())
                        }else{
                            Log.e("getLocalitiesError", "No hay localidades almacenadas en la tabla")
                            _message.postValue(Event("Error de conexion, no hay tablas almacenadas."))
                        }
                        Log.e("getTablesInfoError", getTablesInfoResponse.message)
                    }

                    is NetworkResult.ApiException ->{
                        _message.postValue(Event("Error de conexion, cargando tablas desde la base local."))
                        delay(3000)
                        if(tablesInfoCount != 0){
                            _tableInfoList.postValue(getTablesInfoFromDBUseCase())
                        }else{
                            Log.e("getLocalitiesError", "No hay localidades almacenadas en la tabla")
                            _message.postValue(Event("Error de conexion, no hay tablas almacenadas."))
                        }
                        throw (getTablesInfoResponse.e)
                    }
                }
            }catch (e: Exception){
                _message.postValue(Event("Error de conexion, cargando tablas desde la base local."))
                delay(3000)
                if(tablesInfoCount != 0){
                    _tableInfoList.postValue(getTablesInfoFromDBUseCase())
                }else{
                    Log.e("getLocalitiesError", "No hay localidades almacenadas en la tabla")
                    _message.postValue(Event("Error de conexion, no hay tablas almacenadas."))
                }
                Log.e("getTablesInfoException", e.stackTraceToString())
            }
        }
    }
}