package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = ElectricBlue,
    onPrimary = Color(0xFF031326),
    primaryContainer = ElectricBlueVariant,
    onPrimaryContainer = Color(0xFFDBEAFE),
    secondary = AmberGold,
    onSecondary = Color(0xFF2B1600),
    secondaryContainer = Color(0xFF78350F),
    onSecondaryContainer = LightGold,
    tertiary = Color(0xFF60A5FA),
    background = ObsidianNavy,
    onBackground = DarkTextPrimary,
    surface = MidnightNavy,
    onSurface = DarkTextPrimary,
    surfaceVariant = DeepNavy,
    onSurfaceVariant = DarkTextSecondary,
    outline = BorderNavy,
    outlineVariant = Color(0xFF1E2E4A)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF0284C7),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE0F2FE),
    onPrimaryContainer = Color(0xFF0369A1),
    secondary = Color(0xFFD97706),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFEF3C7),
    onSecondaryContainer = Color(0xFF92400E),
    tertiary = Color(0xFF2563EB),
    background = SlateBackgroundLight,
    onBackground = LightTextPrimary,
    surface = SlateSurfaceLight,
    onSurface = LightTextPrimary,
    surfaceVariant = SlateSurfaceVariantLight,
    onSurfaceVariant = LightTextSecondary,
    outline = Color(0xFFCBD5E1),
    outlineVariant = Color(0xFFE2E8F0)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true, // Default to premium tech dark mode as requested
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
