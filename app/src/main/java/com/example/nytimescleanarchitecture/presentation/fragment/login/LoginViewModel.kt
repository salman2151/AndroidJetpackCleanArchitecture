package com.example.nytimescleanarchitecture.presentation.fragment.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytimescleanarchitecture.data.network.NetworkResponse
import com.example.nytimescleanarchitecture.domain.use_case.user.LoginUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val loginUserUseCase: LoginUserUseCase) : ViewModel() {

    val _shared = MutableSharedFlow<LoginState>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )


    //    private val _loginState = MutableStateFlow(LoginState())
//    val loginStateFlow = _loginState.asStateFlow()
    val loginStateFlow = _shared.asSharedFlow()


    val mldUserName = MutableLiveData<String>()
    val mldPassword = MutableLiveData<String>()

    init {

    }

    fun login() {
        loginUserUseCase(
            userName = mldUserName.value ?: "",
            password = mldPassword.value ?: ""
        ).onEach { itNetworkResponseLoginDto ->
            when (itNetworkResponseLoginDto) {
                is NetworkResponse.Loading -> {
//                    _loginState.value = LoginState(isLoading = true)
                    _shared.tryEmit(LoginState(isLoading = true))
                }
                is NetworkResponse.Error -> {
//                    _loginState.value =
//                        LoginState(error = itNetworkResponseLoginDto.message ?: "Error")
                    _shared.tryEmit(
                        LoginState(
                            error = itNetworkResponseLoginDto.message ?: "Error"
                        )
                    )

                }
                is NetworkResponse.Success -> {
//                    _loginState.value = LoginState(userDto = itNetworkResponseLoginDto.data)
                    _shared.tryEmit(LoginState(userDto = itNetworkResponseLoginDto.data))
                }
            }
        }.launchIn(viewModelScope)
    }

}