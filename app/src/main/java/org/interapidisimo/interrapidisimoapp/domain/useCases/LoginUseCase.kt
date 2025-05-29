package org.interapidisimo.interrapidisimoapp.domain.useCases

import org.interapidisimo.interrapidisimoapp.data.model.LoginRequestBody
import org.interapidisimo.interrapidisimoapp.data.model.LoginResponse
import org.interapidisimo.interrapidisimoapp.data.model.NetworkResult
import org.interapidisimo.interrapidisimoapp.data.repository.MainRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val mainRepository: MainRepository
){
    suspend operator fun invoke(
        loginRequestBody: LoginRequestBody
    ): NetworkResult<LoginResponse> {
        return mainRepository.login(loginRequestBody)
    }
}