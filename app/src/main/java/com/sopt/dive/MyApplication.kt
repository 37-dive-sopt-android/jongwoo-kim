package com.sopt.dive

import android.app.Application
import com.sopt.dive.util.PreferenceUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    companion object {
        lateinit var prefs: PreferenceUtil
    }

    override fun onCreate() {
        super.onCreate()
        prefs = PreferenceUtil(this)
    }
}