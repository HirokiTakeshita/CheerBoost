package com.example.cheerboost.core.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text

@Composable
fun IconChip(
    onClick: () -> Unit,
    text: String,
    imageVector: ImageVector,
    contentDescription: String
) {
    Chip(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        label = {
            Text(text = text)
        },
        icon = {
            Icon(imageVector = imageVector, contentDescription = contentDescription)
        }
    )
}
