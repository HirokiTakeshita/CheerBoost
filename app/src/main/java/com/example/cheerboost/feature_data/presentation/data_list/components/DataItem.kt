package com.example.cheerboost.feature_data.presentation.data_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.*
import com.example.cheerboost.feature_data.domain.model.WorkoutSession
import java.time.format.DateTimeFormatter

@Composable
fun DataItem(
    session: WorkoutSession,
    onClick: () -> Unit,
) {
    Chip(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        label = {
            Text(
                text = "${session.workoutType}: ${session.count}",
                fontWeight = FontWeight.SemiBold
            )
        },
        secondaryLabel = {
            Text(
                text = session.dateTime.format(
                    DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm")
                ),
                fontSize = 7.sp
            )
        },
        colors = ChipDefaults.secondaryChipColors(),
    )
}
