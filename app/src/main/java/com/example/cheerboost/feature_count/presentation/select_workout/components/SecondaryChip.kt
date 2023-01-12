package com.example.cheerboost.feature_count.presentation.select_workout.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Text

@Composable
fun SecondaryChip(
    onClick: () -> Unit,
    text: String,
) {
    Chip(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        label = {
            Text(text = text)
        },
        colors = ChipDefaults.secondaryChipColors()
    )
}
