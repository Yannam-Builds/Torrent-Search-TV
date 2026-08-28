package com.prajwalch.torrentsearch.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

/** Larger type for a ten-foot viewing distance without changing the phone/tablet design. */
val TvTypography = Typography.copy(
    bodySmall = Typography.bodySmall.copy(fontSize = 14.sp, lineHeight = 20.sp),
    bodyMedium = Typography.bodyMedium.copy(fontSize = 16.sp, lineHeight = 22.sp),
    bodyLarge = Typography.bodyLarge.copy(fontSize = 18.sp, lineHeight = 26.sp),
    labelSmall = Typography.labelSmall.copy(fontSize = 12.sp, lineHeight = 16.sp),
    labelMedium = Typography.labelMedium.copy(fontSize = 14.sp, lineHeight = 20.sp),
    labelLarge = Typography.labelLarge.copy(fontSize = 16.sp, lineHeight = 22.sp),
    titleSmall = Typography.titleSmall.copy(fontSize = 16.sp, lineHeight = 22.sp),
    titleMedium = Typography.titleMedium.copy(fontSize = 19.sp, lineHeight = 26.sp),
    titleLarge = Typography.titleLarge.copy(fontSize = 24.sp, lineHeight = 31.sp),
    headlineSmall = Typography.headlineSmall.copy(fontSize = 26.sp, lineHeight = 34.sp),
    headlineMedium = Typography.headlineMedium.copy(fontSize = 30.sp, lineHeight = 38.sp),
    headlineLarge = Typography.headlineLarge.copy(fontSize = 34.sp, lineHeight = 42.sp),
)
