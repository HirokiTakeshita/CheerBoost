package com.example.cheerboost.feature_count.presentation.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import com.example.cheerboost.R
import com.example.cheerboost.core.components.IconChip
import com.example.cheerboost.core.util.Routes
import com.example.cheerboost.core.util.spacing

@Composable
fun MainScreen(
    navController: NavHostController,
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
                    text = stringResource(R.string.app_name),
                    fontWeight = FontWeight.Bold
                )
            }
            item {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            }
            item {
                IconChip(
                    onClick = {
                        navController.navigate(Routes.SelectWorkoutScreen.route)
                    },
                    text = "Workout",
                    imageVector = Icons.Rounded.FitnessCenter,
                    contentDescription = "Workout",
                )
            }
            item {
                IconChip(
                    onClick = {
                        navController.navigate(Routes.DataListScreen.route)
                    },
                    text = "Data",
                    imageVector = Icons.Rounded.SaveAs,
                    contentDescription = "Data"
                )
            }
            item {
                IconChip(
                    onClick = {
                        navController.navigate(Routes.SettingsScreen.route)
                    },
                    text = "Settings",
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = "Settings"
                )
            }
        }
    }
}
