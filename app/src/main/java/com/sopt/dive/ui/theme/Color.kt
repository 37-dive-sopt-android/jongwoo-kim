package com.sopt.dive.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// Default Color
val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)
val TransParent = Color(0x00FF0000)

// Semantic Color
val Error = Color(0xFFFF2222)

// Gray Scale
val MainBlue = Color(0xFF456FFE)
val PurpleBlue = Color(0xFF6588FF)

/** Dark Mode **/
val DarkMainBlue = Color(0xFF6588FF)
val DarkPurpleBlue = Color(0xFF456FFE)


@Immutable
data class DiveColors(
    val white: Color = White,
    val black: Color = Black,
    val transParent: Color = TransParent,

    val backgroundColor: Color = White,
    val textColor: Color = Black,

    val error: Color = Error,

    val mainBlue: Color = MainBlue,
    val purpleBlue: Color = PurpleBlue,
)


val defaultDiveColors = DiveColors(
    white = White,
    black = Black,
    transParent = TransParent,

    backgroundColor = White,
    textColor = Black,

    error = Error,

    mainBlue = MainBlue,
    purpleBlue = PurpleBlue,
)

val darkDiveColor = DiveColors(
    white = White,
    black = Black,
    transParent = TransParent,

    backgroundColor = Black,
    textColor = White,

    error = Error,

    mainBlue = DarkMainBlue,
    purpleBlue = DarkPurpleBlue,
)


val LocalDiveColorsProvider = staticCompositionLocalOf { defaultDiveColors }