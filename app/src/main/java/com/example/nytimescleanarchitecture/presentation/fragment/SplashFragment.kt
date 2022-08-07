package com.example.nytimescleanarchitecture.presentation.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.nytimescleanarchitecture.R
import dagger.hilt.android.HiltAndroidApp


class SplashFragment : BaseFragment() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    override val layoutId: Int
        get() = R.layout.splash_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToArticleListFragment())
            },
            2000
        )
    }


}