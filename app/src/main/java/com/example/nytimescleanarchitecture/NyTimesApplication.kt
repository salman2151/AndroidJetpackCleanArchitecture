package com.example.nytimescleanarchitecture

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NyTimesApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
    }
}