package com.sopt.dive.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

object DiveTheme {
    val colors: DiveColors
        @Composable
        @ReadOnlyComposable
        get() = LocalDiveColorsProvider.current

    val typography: DiveTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalDiveTypographyProvider.current
}

@Composable
fun ProvideCertiColorsAndTypography(
    colors: DiveColors,
    typography: DiveTypography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalDiveColorsProvider provides colors,
        LocalDiveTypographyProvider provides typography,
        content = content
    )
}

@Composable
fun DIVETheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) darkDiveColor else defaultDiveColors

    ProvideCertiColorsAndTypography(
        colors = colors,
        typography = defaultDiveTypography
    ) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            // optional
            SideEffect {
                (view.context as Activity).window.run {
                    WindowCompat.getInsetsController(this, view).isAppearanceLightStatusBars = !darkTheme
                }
            }
        }

        MaterialTheme(
            content = content
        )
    }
}