package com.example.cheerboost.core.util

sealed class Routes(val route: String) {
    object MainScreen : Routes("main")
    object SelectWorkoutScreen : Routes("select_workout")
    object CountScreen : Routes("count")
    object DataListScreen : Routes("data_list")
    object DataDetailScreen : Routes("data_detail")
    object SettingsScreen : Routes("settings")
}
