package com.example.nytimescleanarchitecture.presentation.fragment.login

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.nytimescleanarchitecture.R
import com.example.nytimescleanarchitecture.databinding.LoginFragmentBinding
import com.example.nytimescleanarchitecture.presentation.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    private var binding: LoginFragmentBinding? = null
    private val viewModel : LoginViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.login_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)
        binding?.let { itBinding ->
            itBinding.viewModel = viewModel
        }
    }
}