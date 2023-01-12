package com.example.cheerboost.feature_count.presentation.select_workout

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import com.example.cheerboost.core.util.Routes
import com.example.cheerboost.core.util.WorkoutType
import com.example.cheerboost.core.util.spacing
import com.example.cheerboost.feature_count.presentation.select_workout.components.SecondaryChip

@Composable
fun SelectWorkoutScreen(
    navController: NavHostController
) {
    val listState = rememberScalingLazyListState()
    Scaffold(
        vignette = {
            Vignette(vignettePosition = VignettePosition.TopAndBottom)
        },
        positionIndicator = {
            PositionIndicator(scalingLazyListState = listState)
        }
    ) {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize().padding(horizontal = MaterialTheme.spacing.medium),
            state = listState
        ) {
            item {
                Text(
                    text = "Select Workout",
                    fontWeight = FontWeight.Bold
                )
            }
            item {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            }
            item {
                SecondaryChip(
                    onClick = {
                        navController.navigate(
                            Routes.CountScreen.route + "/${WorkoutType.SIT_UP}"
                        )
                    },
                    text = WorkoutType.SIT_UP,
                )
            }
            item {
                SecondaryChip(
                    onClick = {
                        navController.navigate(
                            Routes.CountScreen.route + "/${WorkoutType.SQUAT}"
                        )
                    },
                    text = WorkoutType.SQUAT,
                )
            }
            item {
                SecondaryChip(
                    onClick = {
                        navController.navigate(
                            Routes.CountScreen.route + "/${WorkoutType.DUMBBELL_PRESS}"
                        )
                    },
                    text = WorkoutType.DUMBBELL_PRESS,
                )
            }
        }
    }
}
