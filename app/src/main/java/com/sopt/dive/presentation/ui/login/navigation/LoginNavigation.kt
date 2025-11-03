package com.sopt.dive.presentation.ui.login.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.core.navigation.Route
import com.sopt.dive.presentation.ui.login.LoginRoute

fun NavController.navigateToLogin(navOptions: NavOptions) {
    navigate(Route.Login, navOptions)
}

fun NavGraphBuilder.loginNavGraph(
    paddingValues: PaddingValues,
    onNavigateToSignUp: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    composable<Route.Login> { backStackEntry ->

        LoginRoute(
            paddingValues = paddingValues,
            navigateToHome = onNavigateToHome,
            navigateToSignUp = onNavigateToSignUp
        )
    }
}
