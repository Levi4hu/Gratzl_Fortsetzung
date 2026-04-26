package com.example.gratzl.shared.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val OfferColorScheme = lightColorScheme(
    primary          = Green80,
    onPrimary        = OnPrimary,
    primaryContainer = Green20,
    secondary        = Teal80,
    onSecondary      = OnSecondary,
    secondaryContainer = Teal20,
    tertiary         = Orange80,
    tertiaryContainer = Orange20,
    background       = Background,
    surface          = Surface,
)

private val RequestColorScheme = lightColorScheme(
    primary          = Teal80,
    onPrimary        = OnSecondary,
    primaryContainer = Teal20,
    secondary        = Green80,
    onSecondary      = OnPrimary,
    secondaryContainer = Green20,
    tertiary         = Orange80,
    tertiaryContainer = Orange20,
    background       = Background,
    surface          = Surface,
)

@Composable
fun MarktplatzTheme(
    isOfferMode: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (isOfferMode) OfferColorScheme else RequestColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography  = AppTypography,
        content     = content
    )
}