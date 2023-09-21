package com.mz.mazen.navigation

import WorkoutLogScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mz.mazen.ui.home.HomeScreen
import com.mz.mazen.ui.profile.ProfileScreen
import com.mz.mazen.ui.settings.SettingsScreen

@Composable
fun MazenNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = Home.route,
        modifier = modifier
    ) {
        composable(route = Home.route) {
            HomeScreen()
        }
        composable(route = Profile.route) {
            ProfileScreen()
        }
        composable(route = Settings.route) {
            SettingsScreen()
        }
        composable(
            route =
                "${WorkoutLog.route}/{${WorkoutLog.workoutTypeArg}}",
            arguments = WorkoutLog.arguments
        ) {
            navBackStackEntry ->
            val workoutType =
                navBackStackEntry.arguments?.getString(WorkoutLog.workoutTypeArg)
            WorkoutLogScreen()
        }
    }
}



