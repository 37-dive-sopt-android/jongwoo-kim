package com.sopt.dive.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.sopt.dive.R

object PretendardFont {
    val Bold = FontFamily(Font(R.font.pretendard_bold))
    val Medium = FontFamily(Font(R.font.pretendard_medium))
    val Regular = FontFamily(Font(R.font.pretendard_regular))
}

sealed interface TypographyTokens {

    @Immutable
    data class Title(
        val bold_24: TextStyle,
        val bold_22: TextStyle
    )

    @Immutable
    data class Subtitle(
        val bold_20: TextStyle,
        val medium_20: TextStyle
    )

    @Immutable
    data class Body(
        val bold_16: TextStyle,
        val medium_16: TextStyle,
        val regular_16: TextStyle
    )
}

@Immutable
data class DiveTypography(
    val title: TypographyTokens.Title,
    val subtitle: TypographyTokens.Subtitle,
    val body: TypographyTokens.Body
)

val defaultDiveTypography = DiveTypography(
    title = TypographyTokens.Title(
        bold_24 = TextStyle(
            fontFamily = PretendardFont.Bold,
            fontSize = 24.sp,
            lineHeight = 28.8.sp, // 120%
            letterSpacing = (-1.6).sp
        ),
        bold_22 = TextStyle(
            fontFamily = PretendardFont.Bold,
            fontSize = 22.sp,
            lineHeight = 26.4.sp, // 120%
            letterSpacing = (-1.4).sp
        )
    ),
    subtitle = TypographyTokens.Subtitle(
        bold_20 = TextStyle(
            fontFamily = PretendardFont.Bold,
            fontSize = 20.sp,
            lineHeight = 26.sp, // 130%
            letterSpacing = (-1.0).sp
        ),
        medium_20 = TextStyle(
            fontFamily = PretendardFont.Medium,
            fontSize = 20.sp,
            lineHeight = 26.sp,
            letterSpacing = (-1.0).sp
        )
    ),
    body = TypographyTokens.Body(
        bold_16 = TextStyle(
            fontFamily = PretendardFont.Bold,
            fontSize = 16.sp,
            lineHeight = 26.sp, // 130%
            letterSpacing = (-1.0).sp
        ),
        medium_16 = TextStyle(
            fontFamily = PretendardFont.Medium,
            fontSize = 16.sp,
            lineHeight = 26.sp,
            letterSpacing = (-1.0).sp
        ),
        regular_16 = TextStyle(
            fontFamily = PretendardFont.Regular,
            fontSize = 16.sp,
            lineHeight = 26.sp,
            letterSpacing = (-1.0).sp
        )
    ),
)

val LocalDiveTypographyProvider = staticCompositionLocalOf { defaultDiveTypography }