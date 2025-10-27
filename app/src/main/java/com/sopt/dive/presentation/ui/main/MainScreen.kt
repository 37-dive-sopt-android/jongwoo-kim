package com.sopt.dive.presentation.ui.main

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sopt.dive.presentation.ui.main.navigation.MainNavHost
import com.sopt.dive.presentation.ui.main.navigation.MainNavigator
import com.sopt.dive.presentation.ui.main.navigation.rememberMainNavigator

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator()
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars),
        content = { padding ->
            MainNavHost(
                navigator = navigator,
                padding = padding
            )
        }
    )
}