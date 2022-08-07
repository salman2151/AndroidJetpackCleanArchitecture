package com.example.nytimescleanarchitecture.presentation.fragment.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytimescleanarchitecture.data.network.NetworkResponse
import com.example.nytimescleanarchitecture.data.room.dto.UserDto
import com.example.nytimescleanarchitecture.domain.repository.UserRepository
import com.example.nytimescleanarchitecture.domain.use_case.user.AddUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val addUserUseCase: AddUserUseCase) :
    ViewModel() {
    private val _splashState = MutableStateFlow(SplashState())
     val splashStateFlow = _splashState.asStateFlow()

    init {
        addUser(UserDto(userName = "Salman2151", password = "Qwe123"))
    }

    private fun addUser(userDto: UserDto) {
        addUserUseCase(userDto = userDto).onEach { itNetworkResponseAddUser ->
            when (itNetworkResponseAddUser) {
                is NetworkResponse.Loading -> {
                    _splashState.value = SplashState(isLoading = true)
                }
                is NetworkResponse.Error -> {
                    _splashState.value =
                        SplashState(error = itNetworkResponseAddUser.message ?: "Error")

                }
                is NetworkResponse.Success -> {
                    _splashState.value = itNetworkResponseAddUser.data?.let {
                        SplashState(userDtoId = itNetworkResponseAddUser.data)
                    } ?: SplashState(
                        error = itNetworkResponseAddUser.message ?: "Id is null, Failed to save"
                    )
                }
            }

        }.launchIn(viewModelScope)
    }
}