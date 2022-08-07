package com.example.nytimescleanarchitecture.presentation.fragment.login

import com.example.nytimescleanarchitecture.data.room.dto.UserDto

data class LoginState(
    val userDto: UserDto? = null,
    val error: String = "",
    val isLoading: Boolean = false
)