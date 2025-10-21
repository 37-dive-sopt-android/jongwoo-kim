package com.sopt.dive.presentation.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.sopt.dive.presentation.ui.main.MainActivity
import com.sopt.dive.presentation.ui.login.LoginActivity
import com.sopt.dive.MyApplication.Companion.prefs
import com.sopt.dive.util.PrefsConst

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val id = prefs.getData(PrefsConst.ID_DATA)
        val pw = prefs.getData(PrefsConst.PW_DATA)

        if(!id.isNullOrEmpty() && !pw.isNullOrEmpty()) {
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
        } else {
            val intent = Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
        }
    }
}