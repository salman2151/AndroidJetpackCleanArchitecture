package com.example.nytimescleanarchitecture.presentation.fragment.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.nytimescleanarchitecture.R
import com.example.nytimescleanarchitecture.databinding.LoginFragmentBinding
import com.example.nytimescleanarchitecture.presentation.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    private var binding: LoginFragmentBinding? = null
    private val viewModel: LoginViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.login_fragment


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)
        binding?.let { itBinding ->
            itBinding.lifecycleOwner = this
            itBinding.viewModel = viewModel
            lifecycleScope.launch {
                viewModel.loginStateFlow.collectLatest {
                    when {
                        it.isLoading -> {
                            setViewVisibility(pbLoadingVisibility = View.VISIBLE)
                        }
                        it.error.isNotEmpty() -> {
                            setViewVisibility(pbLoadingVisibility = View.GONE)
                            Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            setViewVisibility(pbLoadingVisibility = View.GONE)
                            if (it.userDto != null)
                                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToArticleListFragment())
                        }
                    }
                }
            }
        }
    }

    private fun setViewVisibility(
        pbLoadingVisibility: Int = View.GONE,
    ) {
        binding?.pbLoading?.visibility = pbLoadingVisibility
    }
}