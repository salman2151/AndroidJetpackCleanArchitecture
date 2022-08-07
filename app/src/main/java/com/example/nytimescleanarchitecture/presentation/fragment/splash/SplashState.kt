package com.example.nytimescleanarchitecture.presentation.fragment.splash

import com.example.nytimescleanarchitecture.data.room.dto.UserDto

data class SplashState(
    val userDtoId: Long? = null,
    val error: String = "",
    val isLoading: Boolean = false
)