package com.mz.mazen.navigation

import WorkoutLogScreen
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.mz.mazen.R
import com.mz.mazen.ui.etrainer.eTrainerScreen
import com.mz.mazen.ui.home.HomeScreen
import com.mz.mazen.ui.profile.ProfileScreen
import com.mz.mazen.ui.profile.ProfileUiState
import com.mz.mazen.ui.settings.SettingsScreen
import com.mz.mazen.ui.theme.MazenTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,

) {
    NavHost(
        navController = navController,
        startDestination = Home.route,
        modifier = modifier,
    ) {
        val uri = "https://www.bodybuilding.com"
        composable(
            route = Home.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "$uri/category/training"
                    action = Intent.ACTION_VIEW
                }
            ),
        ){workoutEntry ->

            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            HomeScreen(
                drawerState = drawerState,
                navigateToProfile = { /*TODO*/ },
                navigateToWorkoutLog = { /*TODO*/ },
                navigateToWrite = { /*TODO*/ },

            ) {

            }
        }
        composable(route = Profile.route){
            ProfileScreen(
                userData = ProfileUiState(
                    firstName = "",
                    lastName = "",
                    userId = "",
                    displayName = "",
                    position = "",
                    photo = R.drawable.ic_launcher_background
                )
            )
        }
        composable(route = Settings.route){
            SettingsScreen()
        }
        composable(route = WorkoutLog.route){
            WorkoutLogScreen()
        }
        composable(route = Etrainer.route){
            eTrainerScreen()
        }
    }
}
