package com.sopt.dive.presentation.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.MyApplication.Companion.prefs
import com.sopt.dive.R
import com.sopt.dive.util.PrefsConst
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashRoute(
    paddingValues: PaddingValues,
    navigateToLogin: () -> Unit = {},
    navigateToHome: () -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()

    val savedId = prefs.getData(PrefsConst.ID_DATA)
    val savedPw = prefs.getData(PrefsConst.PW_DATA)

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            delay(2000)

            if(savedId.isNullOrEmpty() && savedPw.isNullOrEmpty()) {
                navigateToLogin()
            } else {
                navigateToHome()
            }
        }
    }

    SplashScreen(
        paddingValues = paddingValues
    )
}

@Composable
fun SplashScreen(
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.splash_title),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )
    }
}