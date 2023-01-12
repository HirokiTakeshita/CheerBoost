package com.example.cheerboost.feature_setting.presentation.settings.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.ToggleChip
import androidx.wear.compose.material.ToggleChipDefaults

@Composable
fun RadioChip(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    text: String,
) {
    ToggleChip(
        modifier = Modifier.fillMaxWidth(),
        checked = checked,
        onCheckedChange = onCheckedChange,
        label = {
            Text(text = text)
        },
        toggleControl = {
            Icon(
                imageVector = ToggleChipDefaults.radioIcon(checked = checked),
                contentDescription = if (checked) "Checked" else "Not Checked"
            )
        }
    )
}
