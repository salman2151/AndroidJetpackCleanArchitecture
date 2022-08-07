package com.example.nytimescleanarchitecture.domain.use_case.user

import com.example.nytimescleanarchitecture.data.network.NetworkResponse
import com.example.nytimescleanarchitecture.data.room.dto.UserDto
import com.example.nytimescleanarchitecture.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AddUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke(userDto: UserDto): Flow<NetworkResponse<Long>> = flow {
        try {
            emit(NetworkResponse.Loading())
            val addUserResponse = userRepository.addUser(userDto)
            addUserResponse?.let {
                emit(NetworkResponse.Success(data = it))
            } ?: emit(NetworkResponse.Error(message = "Already Exists"))
            //if(addUserResponse)
        } catch (e: HttpException) {
            emit(NetworkResponse.Error(message = e.message() ?: "HttpException"))
        } catch (e: IOException) {
            emit(NetworkResponse.Error(message = e.message ?: "IOException"))
        } catch (e: Exception) {
            emit(NetworkResponse.Error(message = e.message ?: "Exception"))
        }

    }

}