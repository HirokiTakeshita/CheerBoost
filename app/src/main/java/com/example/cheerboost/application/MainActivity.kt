package com.example.cheerboost.application

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.cheerboost.application.theme.CheerBoostTheme
import com.example.cheerboost.core.util.Routes
import com.example.cheerboost.feature_count.presentation.count.CountScreen
import com.example.cheerboost.feature_count.presentation.main.MainScreen
import com.example.cheerboost.feature_count.presentation.select_workout.SelectWorkoutScreen
import com.example.cheerboost.feature_data.presentation.data_detail.DataDetailScreen
import com.example.cheerboost.feature_data.presentation.data_list.*
import com.example.cheerboost.feature_setting.presentation.settings.SettingsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContent {
            CheerBoostTheme {
                val navController = rememberSwipeDismissableNavController()
                SwipeDismissableNavHost(
                    navController = navController,
                    startDestination = Routes.MainScreen.route
                ) {
                    composable(route = Routes.MainScreen.route) {
                        MainScreen(navController)
                    }
                    composable(route = Routes.SelectWorkoutScreen.route) {
                        SelectWorkoutScreen(navController)
                    }
                    composable(
                        route = Routes.CountScreen.route + "/{workoutType}",
                        arguments = listOf(
                            navArgument("workoutType") {
                                type = NavType.StringType
                            }
                        )
                    ) { entry ->
                        CountScreen(navController, entry.arguments?.getString("workoutType"))
                    }
                    composable(route = Routes.DataListScreen.route) {
                        DataListScreen(navController)
                    }
                    composable(
                        route = Routes.DataDetailScreen.route +
                            "?sessionId={sessionId}&workoutType={workoutType}&count={count}",
                        arguments = listOf(
                            navArgument("sessionId") {
                                type = NavType.IntType
                                defaultValue = -1
                            },
                            navArgument("workoutType") {
                                type = NavType.StringType
                            },
                            navArgument("count") {
                                type = NavType.IntType
                            }
                        )
                    ) {
                        DataDetailScreen(navController)
                    }
                    composable(route = Routes.SettingsScreen.route) {
                        SettingsScreen()
                    }
                }
            }
        }
    }
}
