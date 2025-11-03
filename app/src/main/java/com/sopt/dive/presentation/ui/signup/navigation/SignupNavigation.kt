package com.sopt.dive.presentation.ui.signup.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sopt.dive.core.navigation.Route
import com.sopt.dive.presentation.ui.signup.SignupRoute

fun NavController.navigateToSignup() {
    navigate(Route.Signup)
}

fun NavGraphBuilder.signupNavGraph(
    paddingValues: PaddingValues,
    navController: NavController
) {
    composable<Route.Signup> { backStackEntry ->
        SignupRoute(
            paddingValues = paddingValues,
            navController = navController
        )
    }
}
