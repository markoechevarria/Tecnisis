package com.example.tecnisis.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.tecnisis.ui.theme.backgroundDark
import com.example.tecnisis.ui.theme.backgroundDarkHighContrast
import com.example.tecnisis.ui.theme.backgroundDarkMediumContrast
import com.example.tecnisis.ui.theme.backgroundLight
import com.example.tecnisis.ui.theme.backgroundLightHighContrast
import com.example.tecnisis.ui.theme.backgroundLightMediumContrast
import com.example.tecnisis.ui.theme.errorContainerDark
import com.example.tecnisis.ui.theme.errorContainerDarkHighContrast
import com.example.tecnisis.ui.theme.errorContainerDarkMediumContrast
import com.example.tecnisis.ui.theme.errorContainerLight
import com.example.tecnisis.ui.theme.errorContainerLightHighContrast
import com.example.tecnisis.ui.theme.errorContainerLightMediumContrast
import com.example.tecnisis.ui.theme.errorDark
import com.example.tecnisis.ui.theme.errorDarkHighContrast
import com.example.tecnisis.ui.theme.errorDarkMediumContrast
import com.example.tecnisis.ui.theme.errorLight
import com.example.tecnisis.ui.theme.errorLightHighContrast
import com.example.tecnisis.ui.theme.errorLightMediumContrast
import com.example.tecnisis.ui.theme.inverseOnSurfaceDark
import com.example.tecnisis.ui.theme.inverseOnSurfaceDarkHighContrast
import com.example.tecnisis.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.example.tecnisis.ui.theme.inverseOnSurfaceLight
import com.example.tecnisis.ui.theme.inverseOnSurfaceLightHighContrast
import com.example.tecnisis.ui.theme.inverseOnSurfaceLightMediumContrast
import com.example.tecnisis.ui.theme.inversePrimaryDark
import com.example.tecnisis.ui.theme.inversePrimaryDarkHighContrast
import com.example.tecnisis.ui.theme.inversePrimaryDarkMediumContrast
import com.example.tecnisis.ui.theme.inversePrimaryLight
import com.example.tecnisis.ui.theme.inversePrimaryLightHighContrast
import com.example.tecnisis.ui.theme.inversePrimaryLightMediumContrast
import com.example.tecnisis.ui.theme.inverseSurfaceDark
import com.example.tecnisis.ui.theme.inverseSurfaceDarkHighContrast
import com.example.tecnisis.ui.theme.inverseSurfaceDarkMediumContrast
import com.example.tecnisis.ui.theme.inverseSurfaceLight
import com.example.tecnisis.ui.theme.inverseSurfaceLightHighContrast
import com.example.tecnisis.ui.theme.inverseSurfaceLightMediumContrast
import com.example.tecnisis.ui.theme.onBackgroundDark
import com.example.tecnisis.ui.theme.onBackgroundDarkHighContrast
import com.example.tecnisis.ui.theme.onBackgroundDarkMediumContrast
import com.example.tecnisis.ui.theme.onBackgroundLight
import com.example.tecnisis.ui.theme.onBackgroundLightHighContrast
import com.example.tecnisis.ui.theme.onBackgroundLightMediumContrast
import com.example.tecnisis.ui.theme.onErrorContainerDark
import com.example.tecnisis.ui.theme.onErrorContainerDarkHighContrast
import com.example.tecnisis.ui.theme.onErrorContainerDarkMediumContrast
import com.example.tecnisis.ui.theme.onErrorContainerLight
import com.example.tecnisis.ui.theme.onErrorContainerLightHighContrast
import com.example.tecnisis.ui.theme.onErrorContainerLightMediumContrast
import com.example.tecnisis.ui.theme.onErrorDark
import com.example.tecnisis.ui.theme.onErrorDarkHighContrast
import com.example.tecnisis.ui.theme.onErrorDarkMediumContrast
import com.example.tecnisis.ui.theme.onErrorLight
import com.example.tecnisis.ui.theme.onErrorLightHighContrast
import com.example.tecnisis.ui.theme.onErrorLightMediumContrast
import com.example.tecnisis.ui.theme.onPrimaryContainerDark
import com.example.tecnisis.ui.theme.onPrimaryContainerDarkHighContrast
import com.example.tecnisis.ui.theme.onPrimaryContainerDarkMediumContrast
import com.example.tecnisis.ui.theme.onPrimaryContainerLight
import com.example.tecnisis.ui.theme.onPrimaryContainerLightHighContrast
import com.example.tecnisis.ui.theme.onPrimaryContainerLightMediumContrast
import com.example.tecnisis.ui.theme.onPrimaryDark
import com.example.tecnisis.ui.theme.onPrimaryDarkHighContrast
import com.example.tecnisis.ui.theme.onPrimaryDarkMediumContrast
import com.example.tecnisis.ui.theme.onPrimaryLight
import com.example.tecnisis.ui.theme.onPrimaryLightHighContrast
import com.example.tecnisis.ui.theme.onPrimaryLightMediumContrast
import com.example.tecnisis.ui.theme.onSecondaryContainerDark
import com.example.tecnisis.ui.theme.onSecondaryContainerDarkHighContrast
import com.example.tecnisis.ui.theme.onSecondaryContainerDarkMediumContrast
import com.example.tecnisis.ui.theme.onSecondaryContainerLight
import com.example.tecnisis.ui.theme.onSecondaryContainerLightHighContrast
import com.example.tecnisis.ui.theme.onSecondaryContainerLightMediumContrast
import com.example.tecnisis.ui.theme.onSecondaryDark
import com.example.tecnisis.ui.theme.onSecondaryDarkHighContrast
import com.example.tecnisis.ui.theme.onSecondaryDarkMediumContrast
import com.example.tecnisis.ui.theme.onSecondaryLight
import com.example.tecnisis.ui.theme.onSecondaryLightHighContrast
import com.example.tecnisis.ui.theme.onSecondaryLightMediumContrast
import com.example.tecnisis.ui.theme.onSurfaceDark
import com.example.tecnisis.ui.theme.onSurfaceDarkHighContrast
import com.example.tecnisis.ui.theme.onSurfaceDarkMediumContrast
import com.example.tecnisis.ui.theme.onSurfaceLight
import com.example.tecnisis.ui.theme.onSurfaceLightHighContrast
import com.example.tecnisis.ui.theme.onSurfaceLightMediumContrast
import com.example.tecnisis.ui.theme.onSurfaceVariantDark
import com.example.tecnisis.ui.theme.onSurfaceVariantDarkHighContrast
import com.example.tecnisis.ui.theme.onSurfaceVariantDarkMediumContrast
import com.example.tecnisis.ui.theme.onSurfaceVariantLight
import com.example.tecnisis.ui.theme.onSurfaceVariantLightHighContrast
import com.example.tecnisis.ui.theme.onSurfaceVariantLightMediumContrast
import com.example.tecnisis.ui.theme.onTertiaryContainerDark
import com.example.tecnisis.ui.theme.onTertiaryContainerDarkHighContrast
import com.example.tecnisis.ui.theme.onTertiaryContainerDarkMediumContrast
import com.example.tecnisis.ui.theme.onTertiaryContainerLight
import com.example.tecnisis.ui.theme.onTertiaryContainerLightHighContrast
import com.example.tecnisis.ui.theme.onTertiaryContainerLightMediumContrast
import com.example.tecnisis.ui.theme.onTertiaryDark
import com.example.tecnisis.ui.theme.onTertiaryDarkHighContrast
import com.example.tecnisis.ui.theme.onTertiaryDarkMediumContrast
import com.example.tecnisis.ui.theme.onTertiaryLight
import com.example.tecnisis.ui.theme.onTertiaryLightHighContrast
import com.example.tecnisis.ui.theme.onTertiaryLightMediumContrast
import com.example.tecnisis.ui.theme.outlineDark
import com.example.tecnisis.ui.theme.outlineDarkHighContrast
import com.example.tecnisis.ui.theme.outlineDarkMediumContrast
import com.example.tecnisis.ui.theme.outlineLight
import com.example.tecnisis.ui.theme.outlineLightHighContrast
import com.example.tecnisis.ui.theme.outlineLightMediumContrast
import com.example.tecnisis.ui.theme.outlineVariantDark
import com.example.tecnisis.ui.theme.outlineVariantDarkHighContrast
import com.example.tecnisis.ui.theme.outlineVariantDarkMediumContrast
import com.example.tecnisis.ui.theme.outlineVariantLight
import com.example.tecnisis.ui.theme.outlineVariantLightHighContrast
import com.example.tecnisis.ui.theme.outlineVariantLightMediumContrast
import com.example.tecnisis.ui.theme.primaryContainerDark
import com.example.tecnisis.ui.theme.primaryContainerDarkHighContrast
import com.example.tecnisis.ui.theme.primaryContainerDarkMediumContrast
import com.example.tecnisis.ui.theme.primaryContainerLight
import com.example.tecnisis.ui.theme.primaryContainerLightHighContrast
import com.example.tecnisis.ui.theme.primaryContainerLightMediumContrast
import com.example.tecnisis.ui.theme.primaryDark
import com.example.tecnisis.ui.theme.primaryDarkHighContrast
import com.example.tecnisis.ui.theme.primaryDarkMediumContrast
import com.example.tecnisis.ui.theme.primaryLight
import com.example.tecnisis.ui.theme.primaryLightHighContrast
import com.example.tecnisis.ui.theme.primaryLightMediumContrast
import com.example.tecnisis.ui.theme.scrimDark
import com.example.tecnisis.ui.theme.scrimDarkHighContrast
import com.example.tecnisis.ui.theme.scrimDarkMediumContrast
import com.example.tecnisis.ui.theme.scrimLight
import com.example.tecnisis.ui.theme.scrimLightHighContrast
import com.example.tecnisis.ui.theme.scrimLightMediumContrast
import com.example.tecnisis.ui.theme.secondaryContainerDark
import com.example.tecnisis.ui.theme.secondaryContainerDarkHighContrast
import com.example.tecnisis.ui.theme.secondaryContainerDarkMediumContrast
import com.example.tecnisis.ui.theme.secondaryContainerLight
import com.example.tecnisis.ui.theme.secondaryContainerLightHighContrast
import com.example.tecnisis.ui.theme.secondaryContainerLightMediumContrast
import com.example.tecnisis.ui.theme.secondaryDark
import com.example.tecnisis.ui.theme.secondaryDarkHighContrast
import com.example.tecnisis.ui.theme.secondaryDarkMediumContrast
import com.example.tecnisis.ui.theme.secondaryLight
import com.example.tecnisis.ui.theme.secondaryLightHighContrast
import com.example.tecnisis.ui.theme.secondaryLightMediumContrast
import com.example.tecnisis.ui.theme.surfaceBrightDark
import com.example.tecnisis.ui.theme.surfaceBrightDarkHighContrast
import com.example.tecnisis.ui.theme.surfaceBrightDarkMediumContrast
import com.example.tecnisis.ui.theme.surfaceBrightLight
import com.example.tecnisis.ui.theme.surfaceBrightLightHighContrast
import com.example.tecnisis.ui.theme.surfaceBrightLightMediumContrast
import com.example.tecnisis.ui.theme.surfaceContainerDark
import com.example.tecnisis.ui.theme.surfaceContainerDarkHighContrast
import com.example.tecnisis.ui.theme.surfaceContainerDarkMediumContrast
import com.example.tecnisis.ui.theme.surfaceContainerHighDark
import com.example.tecnisis.ui.theme.surfaceContainerHighDarkHighContrast
import com.example.tecnisis.ui.theme.surfaceContainerHighDarkMediumContrast
import com.example.tecnisis.ui.theme.surfaceContainerHighLight
import com.example.tecnisis.ui.theme.surfaceContainerHighLightHighContrast
import com.example.tecnisis.ui.theme.surfaceContainerHighLightMediumContrast
import com.example.tecnisis.ui.theme.surfaceContainerHighestDark
import com.example.tecnisis.ui.theme.surfaceContainerHighestDarkHighContrast
import com.example.tecnisis.ui.theme.surfaceContainerHighestDarkMediumContrast
import com.example.tecnisis.ui.theme.surfaceContainerHighestLight
import com.example.tecnisis.ui.theme.surfaceContainerHighestLightHighContrast
import com.example.tecnisis.ui.theme.surfaceContainerHighestLightMediumContrast
import com.example.tecnisis.ui.theme.surfaceContainerLight
import com.example.tecnisis.ui.theme.surfaceContainerLightHighContrast
import com.example.tecnisis.ui.theme.surfaceContainerLightMediumContrast
import com.example.tecnisis.ui.theme.surfaceContainerLowDark
import com.example.tecnisis.ui.theme.surfaceContainerLowDarkHighContrast
import com.example.tecnisis.ui.theme.surfaceContainerLowDarkMediumContrast
import com.example.tecnisis.ui.theme.surfaceContainerLowLight
import com.example.tecnisis.ui.theme.surfaceContainerLowLightHighContrast
import com.example.tecnisis.ui.theme.surfaceContainerLowLightMediumContrast
import com.example.tecnisis.ui.theme.surfaceContainerLowestDark
import com.example.tecnisis.ui.theme.surfaceContainerLowestDarkHighContrast
import com.example.tecnisis.ui.theme.surfaceContainerLowestDarkMediumContrast
import com.example.tecnisis.ui.theme.surfaceContainerLowestLight
import com.example.tecnisis.ui.theme.surfaceContainerLowestLightHighContrast
import com.example.tecnisis.ui.theme.surfaceContainerLowestLightMediumContrast
import com.example.tecnisis.ui.theme.surfaceDark
import com.example.tecnisis.ui.theme.surfaceDarkHighContrast
import com.example.tecnisis.ui.theme.surfaceDarkMediumContrast
import com.example.tecnisis.ui.theme.surfaceDimDark
import com.example.tecnisis.ui.theme.surfaceDimDarkHighContrast
import com.example.tecnisis.ui.theme.surfaceDimDarkMediumContrast
import com.example.tecnisis.ui.theme.surfaceDimLight
import com.example.tecnisis.ui.theme.surfaceDimLightHighContrast
import com.example.tecnisis.ui.theme.surfaceDimLightMediumContrast
import com.example.tecnisis.ui.theme.surfaceLight
import com.example.tecnisis.ui.theme.surfaceLightHighContrast
import com.example.tecnisis.ui.theme.surfaceLightMediumContrast
import com.example.tecnisis.ui.theme.surfaceVariantDark
import com.example.tecnisis.ui.theme.surfaceVariantDarkHighContrast
import com.example.tecnisis.ui.theme.surfaceVariantDarkMediumContrast
import com.example.tecnisis.ui.theme.surfaceVariantLight
import com.example.tecnisis.ui.theme.surfaceVariantLightHighContrast
import com.example.tecnisis.ui.theme.surfaceVariantLightMediumContrast
import com.example.tecnisis.ui.theme.tertiaryContainerDark
import com.example.tecnisis.ui.theme.tertiaryContainerDarkHighContrast
import com.example.tecnisis.ui.theme.tertiaryContainerDarkMediumContrast
import com.example.tecnisis.ui.theme.tertiaryContainerLight
import com.example.tecnisis.ui.theme.tertiaryContainerLightHighContrast
import com.example.tecnisis.ui.theme.tertiaryContainerLightMediumContrast
import com.example.tecnisis.ui.theme.tertiaryDark
import com.example.tecnisis.ui.theme.tertiaryDarkHighContrast
import com.example.tecnisis.ui.theme.tertiaryDarkMediumContrast
import com.example.tecnisis.ui.theme.tertiaryLight
import com.example.tecnisis.ui.theme.tertiaryLightHighContrast
import com.example.tecnisis.ui.theme.tertiaryLightMediumContrast
import com.example.ui.theme.AppTypography

private val lightScheme = lightColorScheme(
    primary = com.example.tecnisis.ui.theme.primaryLight,
    onPrimary = com.example.tecnisis.ui.theme.onPrimaryLight,
    primaryContainer = com.example.tecnisis.ui.theme.primaryContainerLight,
    onPrimaryContainer = com.example.tecnisis.ui.theme.onPrimaryContainerLight,
    secondary = com.example.tecnisis.ui.theme.secondaryLight,
    onSecondary = com.example.tecnisis.ui.theme.onSecondaryLight,
    secondaryContainer = com.example.tecnisis.ui.theme.secondaryContainerLight,
    onSecondaryContainer = com.example.tecnisis.ui.theme.onSecondaryContainerLight,
    tertiary = com.example.tecnisis.ui.theme.tertiaryLight,
    onTertiary = com.example.tecnisis.ui.theme.onTertiaryLight,
    tertiaryContainer = com.example.tecnisis.ui.theme.tertiaryContainerLight,
    onTertiaryContainer = com.example.tecnisis.ui.theme.onTertiaryContainerLight,
    error = com.example.tecnisis.ui.theme.errorLight,
    onError = com.example.tecnisis.ui.theme.onErrorLight,
    errorContainer = com.example.tecnisis.ui.theme.errorContainerLight,
    onErrorContainer = com.example.tecnisis.ui.theme.onErrorContainerLight,
    background = com.example.tecnisis.ui.theme.backgroundLight,
    onBackground = com.example.tecnisis.ui.theme.onBackgroundLight,
    surface = com.example.tecnisis.ui.theme.surfaceLight,
    onSurface = com.example.tecnisis.ui.theme.onSurfaceLight,
    surfaceVariant = com.example.tecnisis.ui.theme.surfaceVariantLight,
    onSurfaceVariant = com.example.tecnisis.ui.theme.onSurfaceVariantLight,
    outline = com.example.tecnisis.ui.theme.outlineLight,
    outlineVariant = com.example.tecnisis.ui.theme.outlineVariantLight,
    scrim = com.example.tecnisis.ui.theme.scrimLight,
    inverseSurface = com.example.tecnisis.ui.theme.inverseSurfaceLight,
    inverseOnSurface = com.example.tecnisis.ui.theme.inverseOnSurfaceLight,
    inversePrimary = com.example.tecnisis.ui.theme.inversePrimaryLight,
    surfaceDim = com.example.tecnisis.ui.theme.surfaceDimLight,
    surfaceBright = com.example.tecnisis.ui.theme.surfaceBrightLight,
    surfaceContainerLowest = com.example.tecnisis.ui.theme.surfaceContainerLowestLight,
    surfaceContainerLow = com.example.tecnisis.ui.theme.surfaceContainerLowLight,
    surfaceContainer = com.example.tecnisis.ui.theme.surfaceContainerLight,
    surfaceContainerHigh = com.example.tecnisis.ui.theme.surfaceContainerHighLight,
    surfaceContainerHighest = com.example.tecnisis.ui.theme.surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = com.example.tecnisis.ui.theme.primaryDark,
    onPrimary = com.example.tecnisis.ui.theme.onPrimaryDark,
    primaryContainer = com.example.tecnisis.ui.theme.primaryContainerDark,
    onPrimaryContainer = com.example.tecnisis.ui.theme.onPrimaryContainerDark,
    secondary = com.example.tecnisis.ui.theme.secondaryDark,
    onSecondary = com.example.tecnisis.ui.theme.onSecondaryDark,
    secondaryContainer = com.example.tecnisis.ui.theme.secondaryContainerDark,
    onSecondaryContainer = com.example.tecnisis.ui.theme.onSecondaryContainerDark,
    tertiary = com.example.tecnisis.ui.theme.tertiaryDark,
    onTertiary = com.example.tecnisis.ui.theme.onTertiaryDark,
    tertiaryContainer = com.example.tecnisis.ui.theme.tertiaryContainerDark,
    onTertiaryContainer = com.example.tecnisis.ui.theme.onTertiaryContainerDark,
    error = com.example.tecnisis.ui.theme.errorDark,
    onError = com.example.tecnisis.ui.theme.onErrorDark,
    errorContainer = com.example.tecnisis.ui.theme.errorContainerDark,
    onErrorContainer = com.example.tecnisis.ui.theme.onErrorContainerDark,
    background = com.example.tecnisis.ui.theme.backgroundDark,
    onBackground = com.example.tecnisis.ui.theme.onBackgroundDark,
    surface = com.example.tecnisis.ui.theme.surfaceDark,
    onSurface = com.example.tecnisis.ui.theme.onSurfaceDark,
    surfaceVariant = com.example.tecnisis.ui.theme.surfaceVariantDark,
    onSurfaceVariant = com.example.tecnisis.ui.theme.onSurfaceVariantDark,
    outline = com.example.tecnisis.ui.theme.outlineDark,
    outlineVariant = com.example.tecnisis.ui.theme.outlineVariantDark,
    scrim = com.example.tecnisis.ui.theme.scrimDark,
    inverseSurface = com.example.tecnisis.ui.theme.inverseSurfaceDark,
    inverseOnSurface = com.example.tecnisis.ui.theme.inverseOnSurfaceDark,
    inversePrimary = com.example.tecnisis.ui.theme.inversePrimaryDark,
    surfaceDim = com.example.tecnisis.ui.theme.surfaceDimDark,
    surfaceBright = com.example.tecnisis.ui.theme.surfaceBrightDark,
    surfaceContainerLowest = com.example.tecnisis.ui.theme.surfaceContainerLowestDark,
    surfaceContainerLow = com.example.tecnisis.ui.theme.surfaceContainerLowDark,
    surfaceContainer = com.example.tecnisis.ui.theme.surfaceContainerDark,
    surfaceContainerHigh = com.example.tecnisis.ui.theme.surfaceContainerHighDark,
    surfaceContainerHighest = com.example.tecnisis.ui.theme.surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = com.example.tecnisis.ui.theme.primaryLightMediumContrast,
    onPrimary = com.example.tecnisis.ui.theme.onPrimaryLightMediumContrast,
    primaryContainer = com.example.tecnisis.ui.theme.primaryContainerLightMediumContrast,
    onPrimaryContainer = com.example.tecnisis.ui.theme.onPrimaryContainerLightMediumContrast,
    secondary = com.example.tecnisis.ui.theme.secondaryLightMediumContrast,
    onSecondary = com.example.tecnisis.ui.theme.onSecondaryLightMediumContrast,
    secondaryContainer = com.example.tecnisis.ui.theme.secondaryContainerLightMediumContrast,
    onSecondaryContainer = com.example.tecnisis.ui.theme.onSecondaryContainerLightMediumContrast,
    tertiary = com.example.tecnisis.ui.theme.tertiaryLightMediumContrast,
    onTertiary = com.example.tecnisis.ui.theme.onTertiaryLightMediumContrast,
    tertiaryContainer = com.example.tecnisis.ui.theme.tertiaryContainerLightMediumContrast,
    onTertiaryContainer = com.example.tecnisis.ui.theme.onTertiaryContainerLightMediumContrast,
    error = com.example.tecnisis.ui.theme.errorLightMediumContrast,
    onError = com.example.tecnisis.ui.theme.onErrorLightMediumContrast,
    errorContainer = com.example.tecnisis.ui.theme.errorContainerLightMediumContrast,
    onErrorContainer = com.example.tecnisis.ui.theme.onErrorContainerLightMediumContrast,
    background = com.example.tecnisis.ui.theme.backgroundLightMediumContrast,
    onBackground = com.example.tecnisis.ui.theme.onBackgroundLightMediumContrast,
    surface = com.example.tecnisis.ui.theme.surfaceLightMediumContrast,
    onSurface = com.example.tecnisis.ui.theme.onSurfaceLightMediumContrast,
    surfaceVariant = com.example.tecnisis.ui.theme.surfaceVariantLightMediumContrast,
    onSurfaceVariant = com.example.tecnisis.ui.theme.onSurfaceVariantLightMediumContrast,
    outline = com.example.tecnisis.ui.theme.outlineLightMediumContrast,
    outlineVariant = com.example.tecnisis.ui.theme.outlineVariantLightMediumContrast,
    scrim = com.example.tecnisis.ui.theme.scrimLightMediumContrast,
    inverseSurface = com.example.tecnisis.ui.theme.inverseSurfaceLightMediumContrast,
    inverseOnSurface = com.example.tecnisis.ui.theme.inverseOnSurfaceLightMediumContrast,
    inversePrimary = com.example.tecnisis.ui.theme.inversePrimaryLightMediumContrast,
    surfaceDim = com.example.tecnisis.ui.theme.surfaceDimLightMediumContrast,
    surfaceBright = com.example.tecnisis.ui.theme.surfaceBrightLightMediumContrast,
    surfaceContainerLowest = com.example.tecnisis.ui.theme.surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = com.example.tecnisis.ui.theme.surfaceContainerLowLightMediumContrast,
    surfaceContainer = com.example.tecnisis.ui.theme.surfaceContainerLightMediumContrast,
    surfaceContainerHigh = com.example.tecnisis.ui.theme.surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = com.example.tecnisis.ui.theme.surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = com.example.tecnisis.ui.theme.primaryLightHighContrast,
    onPrimary = com.example.tecnisis.ui.theme.onPrimaryLightHighContrast,
    primaryContainer = com.example.tecnisis.ui.theme.primaryContainerLightHighContrast,
    onPrimaryContainer = com.example.tecnisis.ui.theme.onPrimaryContainerLightHighContrast,
    secondary = com.example.tecnisis.ui.theme.secondaryLightHighContrast,
    onSecondary = com.example.tecnisis.ui.theme.onSecondaryLightHighContrast,
    secondaryContainer = com.example.tecnisis.ui.theme.secondaryContainerLightHighContrast,
    onSecondaryContainer = com.example.tecnisis.ui.theme.onSecondaryContainerLightHighContrast,
    tertiary = com.example.tecnisis.ui.theme.tertiaryLightHighContrast,
    onTertiary = com.example.tecnisis.ui.theme.onTertiaryLightHighContrast,
    tertiaryContainer = com.example.tecnisis.ui.theme.tertiaryContainerLightHighContrast,
    onTertiaryContainer = com.example.tecnisis.ui.theme.onTertiaryContainerLightHighContrast,
    error = com.example.tecnisis.ui.theme.errorLightHighContrast,
    onError = com.example.tecnisis.ui.theme.onErrorLightHighContrast,
    errorContainer = com.example.tecnisis.ui.theme.errorContainerLightHighContrast,
    onErrorContainer = com.example.tecnisis.ui.theme.onErrorContainerLightHighContrast,
    background = com.example.tecnisis.ui.theme.backgroundLightHighContrast,
    onBackground = com.example.tecnisis.ui.theme.onBackgroundLightHighContrast,
    surface = com.example.tecnisis.ui.theme.surfaceLightHighContrast,
    onSurface = com.example.tecnisis.ui.theme.onSurfaceLightHighContrast,
    surfaceVariant = com.example.tecnisis.ui.theme.surfaceVariantLightHighContrast,
    onSurfaceVariant = com.example.tecnisis.ui.theme.onSurfaceVariantLightHighContrast,
    outline = com.example.tecnisis.ui.theme.outlineLightHighContrast,
    outlineVariant = com.example.tecnisis.ui.theme.outlineVariantLightHighContrast,
    scrim = com.example.tecnisis.ui.theme.scrimLightHighContrast,
    inverseSurface = com.example.tecnisis.ui.theme.inverseSurfaceLightHighContrast,
    inverseOnSurface = com.example.tecnisis.ui.theme.inverseOnSurfaceLightHighContrast,
    inversePrimary = com.example.tecnisis.ui.theme.inversePrimaryLightHighContrast,
    surfaceDim = com.example.tecnisis.ui.theme.surfaceDimLightHighContrast,
    surfaceBright = com.example.tecnisis.ui.theme.surfaceBrightLightHighContrast,
    surfaceContainerLowest = com.example.tecnisis.ui.theme.surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = com.example.tecnisis.ui.theme.surfaceContainerLowLightHighContrast,
    surfaceContainer = com.example.tecnisis.ui.theme.surfaceContainerLightHighContrast,
    surfaceContainerHigh = com.example.tecnisis.ui.theme.surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = com.example.tecnisis.ui.theme.surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = com.example.tecnisis.ui.theme.primaryDarkMediumContrast,
    onPrimary = com.example.tecnisis.ui.theme.onPrimaryDarkMediumContrast,
    primaryContainer = com.example.tecnisis.ui.theme.primaryContainerDarkMediumContrast,
    onPrimaryContainer = com.example.tecnisis.ui.theme.onPrimaryContainerDarkMediumContrast,
    secondary = com.example.tecnisis.ui.theme.secondaryDarkMediumContrast,
    onSecondary = com.example.tecnisis.ui.theme.onSecondaryDarkMediumContrast,
    secondaryContainer = com.example.tecnisis.ui.theme.secondaryContainerDarkMediumContrast,
    onSecondaryContainer = com.example.tecnisis.ui.theme.onSecondaryContainerDarkMediumContrast,
    tertiary = com.example.tecnisis.ui.theme.tertiaryDarkMediumContrast,
    onTertiary = com.example.tecnisis.ui.theme.onTertiaryDarkMediumContrast,
    tertiaryContainer = com.example.tecnisis.ui.theme.tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = com.example.tecnisis.ui.theme.onTertiaryContainerDarkMediumContrast,
    error = com.example.tecnisis.ui.theme.errorDarkMediumContrast,
    onError = com.example.tecnisis.ui.theme.onErrorDarkMediumContrast,
    errorContainer = com.example.tecnisis.ui.theme.errorContainerDarkMediumContrast,
    onErrorContainer = com.example.tecnisis.ui.theme.onErrorContainerDarkMediumContrast,
    background = com.example.tecnisis.ui.theme.backgroundDarkMediumContrast,
    onBackground = com.example.tecnisis.ui.theme.onBackgroundDarkMediumContrast,
    surface = com.example.tecnisis.ui.theme.surfaceDarkMediumContrast,
    onSurface = com.example.tecnisis.ui.theme.onSurfaceDarkMediumContrast,
    surfaceVariant = com.example.tecnisis.ui.theme.surfaceVariantDarkMediumContrast,
    onSurfaceVariant = com.example.tecnisis.ui.theme.onSurfaceVariantDarkMediumContrast,
    outline = com.example.tecnisis.ui.theme.outlineDarkMediumContrast,
    outlineVariant = com.example.tecnisis.ui.theme.outlineVariantDarkMediumContrast,
    scrim = com.example.tecnisis.ui.theme.scrimDarkMediumContrast,
    inverseSurface = com.example.tecnisis.ui.theme.inverseSurfaceDarkMediumContrast,
    inverseOnSurface = com.example.tecnisis.ui.theme.inverseOnSurfaceDarkMediumContrast,
    inversePrimary = com.example.tecnisis.ui.theme.inversePrimaryDarkMediumContrast,
    surfaceDim = com.example.tecnisis.ui.theme.surfaceDimDarkMediumContrast,
    surfaceBright = com.example.tecnisis.ui.theme.surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = com.example.tecnisis.ui.theme.surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = com.example.tecnisis.ui.theme.surfaceContainerLowDarkMediumContrast,
    surfaceContainer = com.example.tecnisis.ui.theme.surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = com.example.tecnisis.ui.theme.surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = com.example.tecnisis.ui.theme.surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = com.example.tecnisis.ui.theme.primaryDarkHighContrast,
    onPrimary = com.example.tecnisis.ui.theme.onPrimaryDarkHighContrast,
    primaryContainer = com.example.tecnisis.ui.theme.primaryContainerDarkHighContrast,
    onPrimaryContainer = com.example.tecnisis.ui.theme.onPrimaryContainerDarkHighContrast,
    secondary = com.example.tecnisis.ui.theme.secondaryDarkHighContrast,
    onSecondary = com.example.tecnisis.ui.theme.onSecondaryDarkHighContrast,
    secondaryContainer = com.example.tecnisis.ui.theme.secondaryContainerDarkHighContrast,
    onSecondaryContainer = com.example.tecnisis.ui.theme.onSecondaryContainerDarkHighContrast,
    tertiary = com.example.tecnisis.ui.theme.tertiaryDarkHighContrast,
    onTertiary = com.example.tecnisis.ui.theme.onTertiaryDarkHighContrast,
    tertiaryContainer = com.example.tecnisis.ui.theme.tertiaryContainerDarkHighContrast,
    onTertiaryContainer = com.example.tecnisis.ui.theme.onTertiaryContainerDarkHighContrast,
    error = com.example.tecnisis.ui.theme.errorDarkHighContrast,
    onError = com.example.tecnisis.ui.theme.onErrorDarkHighContrast,
    errorContainer = com.example.tecnisis.ui.theme.errorContainerDarkHighContrast,
    onErrorContainer = com.example.tecnisis.ui.theme.onErrorContainerDarkHighContrast,
    background = com.example.tecnisis.ui.theme.backgroundDarkHighContrast,
    onBackground = com.example.tecnisis.ui.theme.onBackgroundDarkHighContrast,
    surface = com.example.tecnisis.ui.theme.surfaceDarkHighContrast,
    onSurface = com.example.tecnisis.ui.theme.onSurfaceDarkHighContrast,
    surfaceVariant = com.example.tecnisis.ui.theme.surfaceVariantDarkHighContrast,
    onSurfaceVariant = com.example.tecnisis.ui.theme.onSurfaceVariantDarkHighContrast,
    outline = com.example.tecnisis.ui.theme.outlineDarkHighContrast,
    outlineVariant = com.example.tecnisis.ui.theme.outlineVariantDarkHighContrast,
    scrim = com.example.tecnisis.ui.theme.scrimDarkHighContrast,
    inverseSurface = com.example.tecnisis.ui.theme.inverseSurfaceDarkHighContrast,
    inverseOnSurface = com.example.tecnisis.ui.theme.inverseOnSurfaceDarkHighContrast,
    inversePrimary = com.example.tecnisis.ui.theme.inversePrimaryDarkHighContrast,
    surfaceDim = com.example.tecnisis.ui.theme.surfaceDimDarkHighContrast,
    surfaceBright = com.example.tecnisis.ui.theme.surfaceBrightDarkHighContrast,
    surfaceContainerLowest = com.example.tecnisis.ui.theme.surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = com.example.tecnisis.ui.theme.surfaceContainerLowDarkHighContrast,
    surfaceContainer = com.example.tecnisis.ui.theme.surfaceContainerDarkHighContrast,
    surfaceContainerHigh = com.example.tecnisis.ui.theme.surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = com.example.tecnisis.ui.theme.surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun TecnisisTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkScheme
        else -> lightScheme
    }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography,
    content = content
  )
}