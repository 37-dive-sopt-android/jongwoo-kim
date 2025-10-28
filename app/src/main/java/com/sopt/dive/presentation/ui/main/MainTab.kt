package com.sopt.dive.presentation.ui.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.sopt.dive.R
import com.sopt.dive.core.navigation.MainRoute

enum class MainTab(
    @DrawableRes val defaultIconResId: Int,
    val defaultIconColor: Color = Color.Unspecified,
    val selectedIconColor: Color = Color.Cyan,
    val route: MainRoute
) {
    HOME(
        defaultIconResId = R.drawable.icon_home,
        route = MainRoute.Home
    ),
    SEARCH(
        defaultIconResId = R.drawable.icon_search,
        route = MainRoute.Search
    ),
    PROFILE(
        defaultIconResId = R.drawable.ic_profile,
        route = MainRoute.Profile
    );

    companion object {
        @Composable
        fun contains(predicate: @Composable (MainRoute) -> Boolean): Boolean {
            return MainTab.entries.map { it.route }.any { predicate(it) }
        }
    }
}
