
package com.example.cheerboost.feature_count.presentation.count

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import com.example.cheerboost.core.components.IconChip
import com.example.cheerboost.core.util.Routes
import com.example.cheerboost.core.util.spacing

@Composable
fun CountScreen(
    navController: NavHostController,
    workoutType: String?,
    viewModel: CountViewModel = hiltViewModel()
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
        val state = viewModel.state.value
        ScalingLazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = MaterialTheme.spacing.medium),
            state = listState,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = if (state.isSessionStart) "Counting..." else "$workoutType",
                    fontWeight = FontWeight.Bold
                )
            }
            item {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            }
            item {
                Text(
                    text = "${state.count}",
                    fontSize = 32.sp
                )
            }
            item {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            }
            item {
                IconChip(
                    onClick = {
                        viewModel.onEvent(
                            CountEvent.OnStartOrPauseButtonClick("$workoutType")
                        )
                    },
                    text = if (state.isSessionStart) "Pause" else "Start",
                    imageVector =
                    if (state.isSessionStart) Icons.Rounded.Pause else Icons.Rounded.PlayArrow,
                    contentDescription = if (state.isSessionStart) "Pause" else "Start"
                )
            }
            item {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            }
            item {
                IconChip(
                    onClick = {
                        navController.navigate(
                            Routes.DataDetailScreen.route +
                                "?workoutType=$workoutType&count=${state.count}"
                        )
                    },
                    text = "Finish",
                    imageVector = Icons.Rounded.ArrowForward,
                    contentDescription = "Finish Session"
                )
            }
        }
    }
}
