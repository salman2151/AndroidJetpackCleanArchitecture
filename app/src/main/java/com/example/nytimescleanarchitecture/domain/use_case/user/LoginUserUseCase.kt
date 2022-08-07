package com.example.nytimescleanarchitecture.domain.use_case.user

import com.example.nytimescleanarchitecture.data.network.NetworkResponse
import com.example.nytimescleanarchitecture.data.room.dto.UserDto
import com.example.nytimescleanarchitecture.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke(userName: String, password: String): Flow<NetworkResponse<UserDto>> = flow {
        try {
            emit(NetworkResponse.Loading())
            val responseLoginUserDto = userRepository.loginUser(userName, password)
            responseLoginUserDto?.let { itLoginedUserDto ->
                emit(NetworkResponse.Success(data = itLoginedUserDto))
            } ?: emit(NetworkResponse.Error(message = "Incorrect UserName or Password"))

        } catch (e: HttpException) {
            emit(NetworkResponse.Error(message = e.message() ?: "HttpException"))
        } catch (e: IOException) {
            emit(NetworkResponse.Error(message = e.message ?: "IOException"))
        } catch (e: Exception) {
            emit(NetworkResponse.Error(message = e.message ?: "Exception"))
        }
    }

}