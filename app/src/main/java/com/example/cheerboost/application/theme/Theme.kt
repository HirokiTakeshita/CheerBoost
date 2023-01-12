package com.example.cheerboost.application.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.wear.compose.material.MaterialTheme
import com.example.cheerboost.core.util.LocalSpacing
import com.example.cheerboost.core.util.Spacing

@Composable
fun CheerBoostTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalSpacing provides Spacing()) {
        MaterialTheme(
            colors = wearColorPalette,
            typography = Typography,
            content = content
        )
    }
}
