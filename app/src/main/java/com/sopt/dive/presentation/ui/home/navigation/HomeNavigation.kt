package com.sopt.dive.presentation.ui.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.core.navigation.MainRoute
import com.sopt.dive.core.navigation.Route
import com.sopt.dive.presentation.ui.home.HomeRoute

fun NavController.navigateToHome(navOptions: NavOptions) {
    navigate(MainRoute.Home, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    paddingValues: PaddingValues
) {
    composable<MainRoute.Home> { backStackEntry ->
        HomeRoute(
            paddingValues = paddingValues
        )
    }
}
