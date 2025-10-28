package com.sopt.dive.presentation.ui.search.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.core.navigation.MainRoute
import com.sopt.dive.presentation.ui.search.SearchRoute

fun NavController.navigateToSearch(navOptions: NavOptions) {
    navigate(MainRoute.Search, navOptions)
}

fun NavGraphBuilder.searchNavGraph(
    paddingValues: PaddingValues
) {
    composable<MainRoute.Search> { backStackEntry ->
        SearchRoute(
            paddingValues = paddingValues
        )
    }
}
