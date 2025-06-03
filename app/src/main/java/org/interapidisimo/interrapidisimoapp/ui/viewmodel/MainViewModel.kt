package org.interapidisimo.interrapidisimoapp.ui.viewmodel

import android.app.Application
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
import org.interapidisimo.interrapidisimoapp.ui.view.Event
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val application: Application,
    private val getCurrentVersionUseCase: GetCurrentVersionUseCase,
): ViewModel() {

    private val _message = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>> = _message

    fun getCurrentVersion(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val packageInfo = application.packageManager.getPackageInfo(application.packageName, 0)
                val installedVersion = packageInfo.versionCode
                val getCurrentVersionResponse: NetworkResult<String> = getCurrentVersionUseCase()
                when (getCurrentVersionResponse) {
                    is NetworkResult.ApiSuccess -> {
                        val currentVersion = getCurrentVersionResponse.data.toInt()
                        when{
                            installedVersion == currentVersion -> {
                                _message.postValue(Event("Aplicacion actualizada correctamente."))
                            }
                            installedVersion < currentVersion -> {
                                _message.postValue(Event("La version de la aplicacion instalada es menor la version actual disponible."))
                            }
                            installedVersion > currentVersion -> {
                                _message.postValue(Event("La version de la aplicacion instalada es mayor la version actual disponible."))
                            }
                        }
                    }

                    is NetworkResult.ApiError -> {
                        Log.e("getCurrentVersionError", getCurrentVersionResponse.message)
                        _message.postValue(Event("No es posible verificar la version, error de conexion."))
                    }


                    is NetworkResult.ApiException ->{
                        throw (getCurrentVersionResponse.e)
                        _message.postValue(Event("No es posible verificar la version, error de conexion."))
                    }

                }
            }catch (e: Exception){
                Log.e("getCurrentVersionException", e.stackTraceToString())
                _message.postValue(Event("No es posible verificar la version, error de conexion."))
            }
        }
    }
}