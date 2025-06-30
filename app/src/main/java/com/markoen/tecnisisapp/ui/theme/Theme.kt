package com.markoen.tecnisisapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.markoen.ui.theme.AppTypography

private val lightScheme = lightColorScheme(
    primary = com.markoen.tecnisisapp.ui.theme.primaryLight,
    onPrimary = com.markoen.tecnisisapp.ui.theme.onPrimaryLight,
    primaryContainer = com.markoen.tecnisisapp.ui.theme.primaryContainerLight,
    onPrimaryContainer = com.markoen.tecnisisapp.ui.theme.onPrimaryContainerLight,
    secondary = com.markoen.tecnisisapp.ui.theme.secondaryLight,
    onSecondary = com.markoen.tecnisisapp.ui.theme.onSecondaryLight,
    secondaryContainer = com.markoen.tecnisisapp.ui.theme.secondaryContainerLight,
    onSecondaryContainer = com.markoen.tecnisisapp.ui.theme.onSecondaryContainerLight,
    tertiary = com.markoen.tecnisisapp.ui.theme.tertiaryLight,
    onTertiary = com.markoen.tecnisisapp.ui.theme.onTertiaryLight,
    tertiaryContainer = com.markoen.tecnisisapp.ui.theme.tertiaryContainerLight,
    onTertiaryContainer = com.markoen.tecnisisapp.ui.theme.onTertiaryContainerLight,
    error = com.markoen.tecnisisapp.ui.theme.errorLight,
    onError = com.markoen.tecnisisapp.ui.theme.onErrorLight,
    errorContainer = com.markoen.tecnisisapp.ui.theme.errorContainerLight,
    onErrorContainer = com.markoen.tecnisisapp.ui.theme.onErrorContainerLight,
    background = com.markoen.tecnisisapp.ui.theme.backgroundLight,
    onBackground = com.markoen.tecnisisapp.ui.theme.onBackgroundLight,
    surface = com.markoen.tecnisisapp.ui.theme.surfaceLight,
    onSurface = com.markoen.tecnisisapp.ui.theme.onSurfaceLight,
    surfaceVariant = com.markoen.tecnisisapp.ui.theme.surfaceVariantLight,
    onSurfaceVariant = com.markoen.tecnisisapp.ui.theme.onSurfaceVariantLight,
    outline = com.markoen.tecnisisapp.ui.theme.outlineLight,
    outlineVariant = com.markoen.tecnisisapp.ui.theme.outlineVariantLight,
    scrim = com.markoen.tecnisisapp.ui.theme.scrimLight,
    inverseSurface = com.markoen.tecnisisapp.ui.theme.inverseSurfaceLight,
    inverseOnSurface = com.markoen.tecnisisapp.ui.theme.inverseOnSurfaceLight,
    inversePrimary = com.markoen.tecnisisapp.ui.theme.inversePrimaryLight,
    surfaceDim = com.markoen.tecnisisapp.ui.theme.surfaceDimLight,
    surfaceBright = com.markoen.tecnisisapp.ui.theme.surfaceBrightLight,
    surfaceContainerLowest = com.markoen.tecnisisapp.ui.theme.surfaceContainerLowestLight,
    surfaceContainerLow = com.markoen.tecnisisapp.ui.theme.surfaceContainerLowLight,
    surfaceContainer = com.markoen.tecnisisapp.ui.theme.surfaceContainerLight,
    surfaceContainerHigh = com.markoen.tecnisisapp.ui.theme.surfaceContainerHighLight,
    surfaceContainerHighest = com.markoen.tecnisisapp.ui.theme.surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = com.markoen.tecnisisapp.ui.theme.primaryDark,
    onPrimary = com.markoen.tecnisisapp.ui.theme.onPrimaryDark,
    primaryContainer = com.markoen.tecnisisapp.ui.theme.primaryContainerDark,
    onPrimaryContainer = com.markoen.tecnisisapp.ui.theme.onPrimaryContainerDark,
    secondary = com.markoen.tecnisisapp.ui.theme.secondaryDark,
    onSecondary = com.markoen.tecnisisapp.ui.theme.onSecondaryDark,
    secondaryContainer = com.markoen.tecnisisapp.ui.theme.secondaryContainerDark,
    onSecondaryContainer = com.markoen.tecnisisapp.ui.theme.onSecondaryContainerDark,
    tertiary = com.markoen.tecnisisapp.ui.theme.tertiaryDark,
    onTertiary = com.markoen.tecnisisapp.ui.theme.onTertiaryDark,
    tertiaryContainer = com.markoen.tecnisisapp.ui.theme.tertiaryContainerDark,
    onTertiaryContainer = com.markoen.tecnisisapp.ui.theme.onTertiaryContainerDark,
    error = com.markoen.tecnisisapp.ui.theme.errorDark,
    onError = com.markoen.tecnisisapp.ui.theme.onErrorDark,
    errorContainer = com.markoen.tecnisisapp.ui.theme.errorContainerDark,
    onErrorContainer = com.markoen.tecnisisapp.ui.theme.onErrorContainerDark,
    background = com.markoen.tecnisisapp.ui.theme.backgroundDark,
    onBackground = com.markoen.tecnisisapp.ui.theme.onBackgroundDark,
    surface = com.markoen.tecnisisapp.ui.theme.surfaceDark,
    onSurface = com.markoen.tecnisisapp.ui.theme.onSurfaceDark,
    surfaceVariant = com.markoen.tecnisisapp.ui.theme.surfaceVariantDark,
    onSurfaceVariant = com.markoen.tecnisisapp.ui.theme.onSurfaceVariantDark,
    outline = com.markoen.tecnisisapp.ui.theme.outlineDark,
    outlineVariant = com.markoen.tecnisisapp.ui.theme.outlineVariantDark,
    scrim = com.markoen.tecnisisapp.ui.theme.scrimDark,
    inverseSurface = com.markoen.tecnisisapp.ui.theme.inverseSurfaceDark,
    inverseOnSurface = com.markoen.tecnisisapp.ui.theme.inverseOnSurfaceDark,
    inversePrimary = com.markoen.tecnisisapp.ui.theme.inversePrimaryDark,
    surfaceDim = com.markoen.tecnisisapp.ui.theme.surfaceDimDark,
    surfaceBright = com.markoen.tecnisisapp.ui.theme.surfaceBrightDark,
    surfaceContainerLowest = com.markoen.tecnisisapp.ui.theme.surfaceContainerLowestDark,
    surfaceContainerLow = com.markoen.tecnisisapp.ui.theme.surfaceContainerLowDark,
    surfaceContainer = com.markoen.tecnisisapp.ui.theme.surfaceContainerDark,
    surfaceContainerHigh = com.markoen.tecnisisapp.ui.theme.surfaceContainerHighDark,
    surfaceContainerHighest = com.markoen.tecnisisapp.ui.theme.surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = com.markoen.tecnisisapp.ui.theme.primaryLightMediumContrast,
    onPrimary = com.markoen.tecnisisapp.ui.theme.onPrimaryLightMediumContrast,
    primaryContainer = com.markoen.tecnisisapp.ui.theme.primaryContainerLightMediumContrast,
    onPrimaryContainer = com.markoen.tecnisisapp.ui.theme.onPrimaryContainerLightMediumContrast,
    secondary = com.markoen.tecnisisapp.ui.theme.secondaryLightMediumContrast,
    onSecondary = com.markoen.tecnisisapp.ui.theme.onSecondaryLightMediumContrast,
    secondaryContainer = com.markoen.tecnisisapp.ui.theme.secondaryContainerLightMediumContrast,
    onSecondaryContainer = com.markoen.tecnisisapp.ui.theme.onSecondaryContainerLightMediumContrast,
    tertiary = com.markoen.tecnisisapp.ui.theme.tertiaryLightMediumContrast,
    onTertiary = com.markoen.tecnisisapp.ui.theme.onTertiaryLightMediumContrast,
    tertiaryContainer = com.markoen.tecnisisapp.ui.theme.tertiaryContainerLightMediumContrast,
    onTertiaryContainer = com.markoen.tecnisisapp.ui.theme.onTertiaryContainerLightMediumContrast,
    error = com.markoen.tecnisisapp.ui.theme.errorLightMediumContrast,
    onError = com.markoen.tecnisisapp.ui.theme.onErrorLightMediumContrast,
    errorContainer = com.markoen.tecnisisapp.ui.theme.errorContainerLightMediumContrast,
    onErrorContainer = com.markoen.tecnisisapp.ui.theme.onErrorContainerLightMediumContrast,
    background = com.markoen.tecnisisapp.ui.theme.backgroundLightMediumContrast,
    onBackground = com.markoen.tecnisisapp.ui.theme.onBackgroundLightMediumContrast,
    surface = com.markoen.tecnisisapp.ui.theme.surfaceLightMediumContrast,
    onSurface = com.markoen.tecnisisapp.ui.theme.onSurfaceLightMediumContrast,
    surfaceVariant = com.markoen.tecnisisapp.ui.theme.surfaceVariantLightMediumContrast,
    onSurfaceVariant = com.markoen.tecnisisapp.ui.theme.onSurfaceVariantLightMediumContrast,
    outline = com.markoen.tecnisisapp.ui.theme.outlineLightMediumContrast,
    outlineVariant = com.markoen.tecnisisapp.ui.theme.outlineVariantLightMediumContrast,
    scrim = com.markoen.tecnisisapp.ui.theme.scrimLightMediumContrast,
    inverseSurface = com.markoen.tecnisisapp.ui.theme.inverseSurfaceLightMediumContrast,
    inverseOnSurface = com.markoen.tecnisisapp.ui.theme.inverseOnSurfaceLightMediumContrast,
    inversePrimary = com.markoen.tecnisisapp.ui.theme.inversePrimaryLightMediumContrast,
    surfaceDim = com.markoen.tecnisisapp.ui.theme.surfaceDimLightMediumContrast,
    surfaceBright = com.markoen.tecnisisapp.ui.theme.surfaceBrightLightMediumContrast,
    surfaceContainerLowest = com.markoen.tecnisisapp.ui.theme.surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = com.markoen.tecnisisapp.ui.theme.surfaceContainerLowLightMediumContrast,
    surfaceContainer = com.markoen.tecnisisapp.ui.theme.surfaceContainerLightMediumContrast,
    surfaceContainerHigh = com.markoen.tecnisisapp.ui.theme.surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = com.markoen.tecnisisapp.ui.theme.surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = com.markoen.tecnisisapp.ui.theme.primaryLightHighContrast,
    onPrimary = com.markoen.tecnisisapp.ui.theme.onPrimaryLightHighContrast,
    primaryContainer = com.markoen.tecnisisapp.ui.theme.primaryContainerLightHighContrast,
    onPrimaryContainer = com.markoen.tecnisisapp.ui.theme.onPrimaryContainerLightHighContrast,
    secondary = com.markoen.tecnisisapp.ui.theme.secondaryLightHighContrast,
    onSecondary = com.markoen.tecnisisapp.ui.theme.onSecondaryLightHighContrast,
    secondaryContainer = com.markoen.tecnisisapp.ui.theme.secondaryContainerLightHighContrast,
    onSecondaryContainer = com.markoen.tecnisisapp.ui.theme.onSecondaryContainerLightHighContrast,
    tertiary = com.markoen.tecnisisapp.ui.theme.tertiaryLightHighContrast,
    onTertiary = com.markoen.tecnisisapp.ui.theme.onTertiaryLightHighContrast,
    tertiaryContainer = com.markoen.tecnisisapp.ui.theme.tertiaryContainerLightHighContrast,
    onTertiaryContainer = com.markoen.tecnisisapp.ui.theme.onTertiaryContainerLightHighContrast,
    error = com.markoen.tecnisisapp.ui.theme.errorLightHighContrast,
    onError = com.markoen.tecnisisapp.ui.theme.onErrorLightHighContrast,
    errorContainer = com.markoen.tecnisisapp.ui.theme.errorContainerLightHighContrast,
    onErrorContainer = com.markoen.tecnisisapp.ui.theme.onErrorContainerLightHighContrast,
    background = com.markoen.tecnisisapp.ui.theme.backgroundLightHighContrast,
    onBackground = com.markoen.tecnisisapp.ui.theme.onBackgroundLightHighContrast,
    surface = com.markoen.tecnisisapp.ui.theme.surfaceLightHighContrast,
    onSurface = com.markoen.tecnisisapp.ui.theme.onSurfaceLightHighContrast,
    surfaceVariant = com.markoen.tecnisisapp.ui.theme.surfaceVariantLightHighContrast,
    onSurfaceVariant = com.markoen.tecnisisapp.ui.theme.onSurfaceVariantLightHighContrast,
    outline = com.markoen.tecnisisapp.ui.theme.outlineLightHighContrast,
    outlineVariant = com.markoen.tecnisisapp.ui.theme.outlineVariantLightHighContrast,
    scrim = com.markoen.tecnisisapp.ui.theme.scrimLightHighContrast,
    inverseSurface = com.markoen.tecnisisapp.ui.theme.inverseSurfaceLightHighContrast,
    inverseOnSurface = com.markoen.tecnisisapp.ui.theme.inverseOnSurfaceLightHighContrast,
    inversePrimary = com.markoen.tecnisisapp.ui.theme.inversePrimaryLightHighContrast,
    surfaceDim = com.markoen.tecnisisapp.ui.theme.surfaceDimLightHighContrast,
    surfaceBright = com.markoen.tecnisisapp.ui.theme.surfaceBrightLightHighContrast,
    surfaceContainerLowest = com.markoen.tecnisisapp.ui.theme.surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = com.markoen.tecnisisapp.ui.theme.surfaceContainerLowLightHighContrast,
    surfaceContainer = com.markoen.tecnisisapp.ui.theme.surfaceContainerLightHighContrast,
    surfaceContainerHigh = com.markoen.tecnisisapp.ui.theme.surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = com.markoen.tecnisisapp.ui.theme.surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = com.markoen.tecnisisapp.ui.theme.primaryDarkMediumContrast,
    onPrimary = com.markoen.tecnisisapp.ui.theme.onPrimaryDarkMediumContrast,
    primaryContainer = com.markoen.tecnisisapp.ui.theme.primaryContainerDarkMediumContrast,
    onPrimaryContainer = com.markoen.tecnisisapp.ui.theme.onPrimaryContainerDarkMediumContrast,
    secondary = com.markoen.tecnisisapp.ui.theme.secondaryDarkMediumContrast,
    onSecondary = com.markoen.tecnisisapp.ui.theme.onSecondaryDarkMediumContrast,
    secondaryContainer = com.markoen.tecnisisapp.ui.theme.secondaryContainerDarkMediumContrast,
    onSecondaryContainer = com.markoen.tecnisisapp.ui.theme.onSecondaryContainerDarkMediumContrast,
    tertiary = com.markoen.tecnisisapp.ui.theme.tertiaryDarkMediumContrast,
    onTertiary = com.markoen.tecnisisapp.ui.theme.onTertiaryDarkMediumContrast,
    tertiaryContainer = com.markoen.tecnisisapp.ui.theme.tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = com.markoen.tecnisisapp.ui.theme.onTertiaryContainerDarkMediumContrast,
    error = com.markoen.tecnisisapp.ui.theme.errorDarkMediumContrast,
    onError = com.markoen.tecnisisapp.ui.theme.onErrorDarkMediumContrast,
    errorContainer = com.markoen.tecnisisapp.ui.theme.errorContainerDarkMediumContrast,
    onErrorContainer = com.markoen.tecnisisapp.ui.theme.onErrorContainerDarkMediumContrast,
    background = com.markoen.tecnisisapp.ui.theme.backgroundDarkMediumContrast,
    onBackground = com.markoen.tecnisisapp.ui.theme.onBackgroundDarkMediumContrast,
    surface = com.markoen.tecnisisapp.ui.theme.surfaceDarkMediumContrast,
    onSurface = com.markoen.tecnisisapp.ui.theme.onSurfaceDarkMediumContrast,
    surfaceVariant = com.markoen.tecnisisapp.ui.theme.surfaceVariantDarkMediumContrast,
    onSurfaceVariant = com.markoen.tecnisisapp.ui.theme.onSurfaceVariantDarkMediumContrast,
    outline = com.markoen.tecnisisapp.ui.theme.outlineDarkMediumContrast,
    outlineVariant = com.markoen.tecnisisapp.ui.theme.outlineVariantDarkMediumContrast,
    scrim = com.markoen.tecnisisapp.ui.theme.scrimDarkMediumContrast,
    inverseSurface = com.markoen.tecnisisapp.ui.theme.inverseSurfaceDarkMediumContrast,
    inverseOnSurface = com.markoen.tecnisisapp.ui.theme.inverseOnSurfaceDarkMediumContrast,
    inversePrimary = com.markoen.tecnisisapp.ui.theme.inversePrimaryDarkMediumContrast,
    surfaceDim = com.markoen.tecnisisapp.ui.theme.surfaceDimDarkMediumContrast,
    surfaceBright = com.markoen.tecnisisapp.ui.theme.surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = com.markoen.tecnisisapp.ui.theme.surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = com.markoen.tecnisisapp.ui.theme.surfaceContainerLowDarkMediumContrast,
    surfaceContainer = com.markoen.tecnisisapp.ui.theme.surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = com.markoen.tecnisisapp.ui.theme.surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = com.markoen.tecnisisapp.ui.theme.surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = com.markoen.tecnisisapp.ui.theme.primaryDarkHighContrast,
    onPrimary = com.markoen.tecnisisapp.ui.theme.onPrimaryDarkHighContrast,
    primaryContainer = com.markoen.tecnisisapp.ui.theme.primaryContainerDarkHighContrast,
    onPrimaryContainer = com.markoen.tecnisisapp.ui.theme.onPrimaryContainerDarkHighContrast,
    secondary = com.markoen.tecnisisapp.ui.theme.secondaryDarkHighContrast,
    onSecondary = com.markoen.tecnisisapp.ui.theme.onSecondaryDarkHighContrast,
    secondaryContainer = com.markoen.tecnisisapp.ui.theme.secondaryContainerDarkHighContrast,
    onSecondaryContainer = com.markoen.tecnisisapp.ui.theme.onSecondaryContainerDarkHighContrast,
    tertiary = com.markoen.tecnisisapp.ui.theme.tertiaryDarkHighContrast,
    onTertiary = com.markoen.tecnisisapp.ui.theme.onTertiaryDarkHighContrast,
    tertiaryContainer = com.markoen.tecnisisapp.ui.theme.tertiaryContainerDarkHighContrast,
    onTertiaryContainer = com.markoen.tecnisisapp.ui.theme.onTertiaryContainerDarkHighContrast,
    error = com.markoen.tecnisisapp.ui.theme.errorDarkHighContrast,
    onError = com.markoen.tecnisisapp.ui.theme.onErrorDarkHighContrast,
    errorContainer = com.markoen.tecnisisapp.ui.theme.errorContainerDarkHighContrast,
    onErrorContainer = com.markoen.tecnisisapp.ui.theme.onErrorContainerDarkHighContrast,
    background = com.markoen.tecnisisapp.ui.theme.backgroundDarkHighContrast,
    onBackground = com.markoen.tecnisisapp.ui.theme.onBackgroundDarkHighContrast,
    surface = com.markoen.tecnisisapp.ui.theme.surfaceDarkHighContrast,
    onSurface = com.markoen.tecnisisapp.ui.theme.onSurfaceDarkHighContrast,
    surfaceVariant = com.markoen.tecnisisapp.ui.theme.surfaceVariantDarkHighContrast,
    onSurfaceVariant = com.markoen.tecnisisapp.ui.theme.onSurfaceVariantDarkHighContrast,
    outline = com.markoen.tecnisisapp.ui.theme.outlineDarkHighContrast,
    outlineVariant = com.markoen.tecnisisapp.ui.theme.outlineVariantDarkHighContrast,
    scrim = com.markoen.tecnisisapp.ui.theme.scrimDarkHighContrast,
    inverseSurface = com.markoen.tecnisisapp.ui.theme.inverseSurfaceDarkHighContrast,
    inverseOnSurface = com.markoen.tecnisisapp.ui.theme.inverseOnSurfaceDarkHighContrast,
    inversePrimary = com.markoen.tecnisisapp.ui.theme.inversePrimaryDarkHighContrast,
    surfaceDim = com.markoen.tecnisisapp.ui.theme.surfaceDimDarkHighContrast,
    surfaceBright = com.markoen.tecnisisapp.ui.theme.surfaceBrightDarkHighContrast,
    surfaceContainerLowest = com.markoen.tecnisisapp.ui.theme.surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = com.markoen.tecnisisapp.ui.theme.surfaceContainerLowDarkHighContrast,
    surfaceContainer = com.markoen.tecnisisapp.ui.theme.surfaceContainerDarkHighContrast,
    surfaceContainerHigh = com.markoen.tecnisisapp.ui.theme.surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = com.markoen.tecnisisapp.ui.theme.surfaceContainerHighestDarkHighContrast,
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
    val colorScheme = /* when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkScheme
        else -> lightScheme
    }*/
        if (darkTheme) {darkScheme} else {lightScheme}

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography,
    content = content
  )
}