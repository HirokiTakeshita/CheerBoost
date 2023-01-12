package com.example.cheerboost.feature_data.presentation.data_list

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import com.example.cheerboost.core.util.Routes
import com.example.cheerboost.core.util.spacing
import com.example.cheerboost.feature_data.presentation.data_list.components.DataItem

@Composable
fun DataListScreen(
    navController: NavHostController,
    viewModel: DataListViewModel = hiltViewModel()
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
            modifier = Modifier.fillMaxSize().padding(horizontal = MaterialTheme.spacing.medium),
            state = listState
        ) {
            item {
                Text(
                    text = "Data",
                    fontWeight = FontWeight.Bold
                )
            }
            item {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            }
            items(state.sessions) { session ->
                DataItem(
                    session = session,
                    onClick = {
                        navController.navigate(
                            Routes.DataDetailScreen.route +
                                "?sessionId=${session.id}&workoutType=${session.workoutType}" +
                                "&count=${session.count}"
                        )
                    },
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            }
            item {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            }
            item {
                Button(
                    modifier = Modifier.fillMaxWidth().padding(MaterialTheme.spacing.medium),
                    onClick = {
                        navController.navigate(Routes.MainScreen.route)
                    }
                ) {
                    Text(text = "Back to Home")
                }
            }
        }
    }
}
