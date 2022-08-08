package com.example.nytimescleanarchitecture.data.repository

import com.example.nytimescleanarchitecture.data.room.dao.UserDao
import com.example.nytimescleanarchitecture.data.room.dto.UserDto
import com.example.nytimescleanarchitecture.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UserRepository {
    override suspend fun addUser(userDto: UserDto): Long? {
        return userDao.insert(userDto)
    }

    override suspend fun loginUser(userName: String, password: String): UserDto? {
        return userDao.getLoginUser(userName, password)
    }
}