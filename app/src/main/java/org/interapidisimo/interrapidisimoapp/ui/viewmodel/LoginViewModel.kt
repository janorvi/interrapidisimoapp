package org.interapidisimo.interrapidisimoapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.interapidisimo.interrapidisimoapp.data.model.*
import org.interapidisimo.interrapidisimoapp.domain.useCases.network.LoginUseCase
import org.interapidisimo.interrapidisimoapp.domain.useCases.users.*
import org.interapidisimo.interrapidisimoapp.ui.view.Event
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUsersCountUseCase: GetUsersCountUseCase,
    private val insertUserUseCase: InsertUserUseCase,
    private val getUsersFromDBUseCase: GetUsersFromDBUseCase,
    private val loginUseCase: LoginUseCase,
): ViewModel() {

    private val _message = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>> = _message

    private val _isLoggedIn = MutableLiveData(false)
    val isLoggedIn: MutableLiveData<Boolean> = _isLoggedIn

    fun login(user: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val usersCount = getUsersCountUseCase()
            try {
                val loginResponse: NetworkResult<LoginResponse> = loginUseCase(
                    loginRequestBody = LoginRequestBody(
                        mac = "",
                        applicationName = "Controller APP",
                        password = password,
                        path = "",
                        user = user
                    )
                )
                when (loginResponse) {
                    is NetworkResult.ApiSuccess -> {
                        _isLoggedIn.postValue(true)
                        if(usersCount == 0){
                            _message.postValue(Event("Login exitoso, guardando usuario localmente."))
                            insertUserUseCase(loginResponse.data)
                        }else{
                            if(loginResponse.data.user.isNotEmpty()){
                                _message.postValue(Event("Login exitoso, Hola: ${loginResponse.data.name}"))
                            }else{
                                _message.postValue(Event("Login fallido, digite un usuario y contraseña validos."))
                            }
                        }
                    }

                    is NetworkResult.ApiError -> {
                        _message.postValue(Event("Login fallido."))
                        delay(3000)
                        if(usersCount != 0){
                            getUsersFromDBUseCase()
                        }else{
                            Log.e("loginError", "No hay usuarios almacenados en la tabla")
                            _message.postValue(Event("Login fallido, no existe un usuario local"))
                        }
                        Log.e("loginError", loginResponse.message)
                    }

                    is NetworkResult.ApiException ->{
                        _message.postValue(Event("Error de conexion, iniciando con usuario local."))
                        delay(3000)
                        if(usersCount != 0){
                            getUsersFromDBUseCase()
                        }else{
                            Log.e("loginError", "No hay usuarios almacenados en la tabla.")
                            _message.postValue(Event("Login fallido, no existe un usuario local."))
                        }
                        throw (loginResponse.e)
                    }
                }
            } catch (e: Exception) {
                _message.postValue(Event("Login fallido."))
                delay(3000)
                if(usersCount != 0){
                    getUsersFromDBUseCase()
                }else{
                    Log.e("loginError", "No hay usuarios almacenados en la tabla.")
                    _message.postValue(Event("Login fallido, no existe un usuario local."))
                }
                Log.e("loginException", e.stackTraceToString())
            }
        }
    }
}