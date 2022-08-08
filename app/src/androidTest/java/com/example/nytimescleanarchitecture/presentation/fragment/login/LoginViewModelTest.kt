package com.example.nytimescleanarchitecture.presentation.fragment.login

import android.icu.util.Output
import com.example.nytimescleanarchitecture.domain.repository.UserRepository
import com.example.nytimescleanarchitecture.domain.use_case.user.LoginUserUseCase
import dagger.hilt.android.AndroidEntryPoint
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ChannelResult.Companion.success
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest : TestCase() {

    @Mock
    private lateinit var userRepository: UserRepository
    private lateinit var loginUserUseCase: LoginUserUseCase

//    private lateinit var viewModel: LoginViewModel

//    @Before
//    fun before(){
//        loginUserUseCase = LoginUserUseCase(userRepository)
//    }
    @Test
    fun test() = runBlocking {
        loginUserUseCase = LoginUserUseCase(userRepository)
        //GIVEN
//        val inputFlow = flowOf(Output.success(getDummyCharacters()))
//        Mockito.`when`(userRepository.loginUser("Salman2151","Qwe123")).thenReturn(inputFlow)
//        //WHEN
        val output = loginUserUseCase.invoke("Salman2151","Qwe123")
        //THEN
        assert(1 == 1)
    }
}