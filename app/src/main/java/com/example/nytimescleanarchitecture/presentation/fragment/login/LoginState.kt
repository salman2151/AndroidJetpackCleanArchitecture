package com.example.nytimescleanarchitecture.presentation.fragment.login

import com.example.nytimescleanarchitecture.data.room.dto.UserDto
import kotlin.random.Random


data class LoginState(
    val userDto: UserDto? = null,
    val error: String = "",
    val isLoading: Boolean = false

) {
    // methods added for multiple emits, when user enter multiple times wrong credentials to show toast
    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        return Random.nextInt()
    }
}