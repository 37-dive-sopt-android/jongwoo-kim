package com.sopt.dive.presentation.ui.splash.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sopt.dive.core.navigation.Route
import com.sopt.dive.presentation.ui.splash.SplashRoute

fun NavGraphBuilder.splashNavGraph(
    paddingValues: PaddingValues,
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    composable<Route.Splash> {
        SplashRoute(
            paddingValues = paddingValues,
            navigateToLogin = onNavigateToLogin,
            navigateToHome = onNavigateToHome
        )
    }
}
