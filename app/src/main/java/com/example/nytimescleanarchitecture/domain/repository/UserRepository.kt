package com.example.nytimescleanarchitecture.domain.repository

import com.example.nytimescleanarchitecture.data.room.dto.UserDto

interface UserRepository {
    suspend fun addUser(userDto: UserDto): Long?
   suspend fun loginUser(userName: String, password: String) : UserDto?
}