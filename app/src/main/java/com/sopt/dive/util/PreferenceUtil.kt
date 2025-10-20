package com.sopt.dive.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class PreferenceUtil(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)

    fun getData(key: String): String? = prefs.getString(key, "")
    fun setData(key: String, value: String) = prefs.edit(commit = true) { putString(key, value) }
}