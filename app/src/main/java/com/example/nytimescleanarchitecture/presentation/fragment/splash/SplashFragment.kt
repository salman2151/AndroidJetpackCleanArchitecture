package com.example.nytimescleanarchitecture.presentation.fragment.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.nytimescleanarchitecture.R
import com.example.nytimescleanarchitecture.databinding.SplashFragmentBinding
import com.example.nytimescleanarchitecture.presentation.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    private var binding: SplashFragmentBinding? = null
    private val viewModel: SplashViewModel by viewModels()


    companion object {
        fun newInstance() = SplashFragment()
    }

    override val layoutId: Int
        get() = R.layout.splash_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)

        lifecycleScope.launch {
            viewModel.splashStateFlow.collectLatest {
                when {
                    it.isLoading -> {
                        setViewVisibility(pbLoadingVisibility = View.VISIBLE)
                    }
                    it.error.isEmpty().not() -> {
                        setViewVisibility(pbLoadingVisibility = View.GONE)
                        Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
                    }
                    else -> {
                        setViewVisibility(pbLoadingVisibility = View.GONE)
                        Handler(Looper.getMainLooper()).postDelayed(
                            {
                                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
                            },
                            2000
                        )
                    }
                }
            }
        }
//        Handler(Looper.getMainLooper()).postDelayed(
//            {
//
//            },
//            2000
//        )
    }

    private fun setViewVisibility(
        pbLoadingVisibility: Int = View.GONE,
    ) {
        binding?.pbLoading?.visibility = pbLoadingVisibility


    }


}