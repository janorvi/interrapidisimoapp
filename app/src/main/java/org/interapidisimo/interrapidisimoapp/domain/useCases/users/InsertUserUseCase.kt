package org.interapidisimo.interrapidisimoapp.domain.useCases.users

import org.interapidisimo.interrapidisimoapp.data.database.entities.UserEntity
import org.interapidisimo.interrapidisimoapp.data.model.LoginResponse
import org.interapidisimo.interrapidisimoapp.data.repository.MainRepository
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(loginResponse: LoginResponse) {

        mainRepository.insertUser(
            userEntity = UserEntity(
                user = loginResponse.user,
                identification = loginResponse.identification,
                name = loginResponse.name
            )
        )
    }
}