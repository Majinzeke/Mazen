package com.mz.mazen.navigation

import WorkoutLogScreen
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mz.mazen.ui.home.HomeScreen
import com.mz.mazen.ui.home.HomeScreenBottomBar
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
        startDestination = MazenNavigationActions.Home.route,
        modifier = modifier,
    ) {
        homeRoute(

            navigateToProfile = {
            },
            navigateToSettings = {
                navController.navigate("settings")

            },
            navigateToWorkoutLog = {
                navController.navigate("workout_log")

            }
        )
        settingsRoute(

        )
        profileRoute(

        )


    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.homeRoute(
    navigateToProfile: () -> Unit,
    navigateToSettings: () -> Unit,
    navigateToWorkoutLog: () -> Unit,
) {

    composable(route = MazenNavigationActions.Home.route) {

        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val navController = rememberNavController()

        MazenTheme {
            Scaffold (
                bottomBar = { HomeScreenBottomBar() }
            ){

                HomeScreen(
                    drawerState = drawerState,
                    navigateToProfile = {
                                        navController.navigate(MazenNavigationActions.Profile.route)
                                        },
                    navigateToWorkoutLog = { /*TODO*/ },
                    navigateToWrite = { /*TODO*/ },


                ) {

                }

            }
        }

        HomeScreen(
            drawerState = drawerState,
            navigateToProfile = { /*TODO*/ },
            navigateToWorkoutLog = { /*TODO*/ },
            navigateToWrite = { /*TODO*/ }) {
            
        }

    }
}

fun NavGraphBuilder.profileRoute() {
    composable(
        route =
        "${MazenNavigationActions.WorkoutLog.route}/{${MazenNavigationActions.WorkoutLog.workoutTypeArg}}",
        arguments = MazenNavigationActions.WorkoutLog.arguments
    ) { navBackStackEntry ->
        val workoutType =
            navBackStackEntry.arguments?.getString(MazenNavigationActions.WorkoutLog.workoutTypeArg)
        WorkoutLogScreen()
    }
}

fun NavGraphBuilder.settingsRoute() {
    composable(route = MazenNavigationActions.Settings.route) {
        SettingsScreen()
    }
}

