package com.example.nytimescleanarchitecture.presentation.fragment.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytimescleanarchitecture.data.network.NetworkResponse
import com.example.nytimescleanarchitecture.domain.use_case.user.LoginUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val loginUserUseCase: LoginUserUseCase) : ViewModel() {


    private val _loginState = MutableStateFlow(LoginState())
    val loginStateFlow = _loginState.asStateFlow()


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
                    _loginState.value = LoginState(isLoading = true)
                }
                is NetworkResponse.Error -> {
                    _loginState.value =
                        LoginState(error = itNetworkResponseLoginDto.message ?: "Error")

                }
                is NetworkResponse.Success -> {
                    _loginState.value = LoginState(userDto = itNetworkResponseLoginDto.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}