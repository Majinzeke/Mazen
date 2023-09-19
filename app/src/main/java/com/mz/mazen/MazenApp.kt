package com.mz.mazen

import WorkoutLogScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mz.mazen.data.model.WorkoutPost
import com.mz.mazen.navigation.MazenNavigationActions
import com.mz.mazen.navigation.MazenRoute
import com.mz.mazen.ui.home.HomeScreen
import com.mz.mazen.ui.profile.ProfileScreen
import com.mz.mazen.ui.settings.SettingsScreen

@Composable
fun MazenApp(){

    val navController = rememberNavController()
    MazenNavHost(navController = navController)


}

@Composable
private fun MazenNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    val navigationActions = remember(navController) {
        MazenNavigationActions(navController)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedDestination =
        navBackStackEntry?.destination?.route ?: MazenRoute.HOME

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = MazenRoute.HOME,
    ) {
        composable(MazenRoute.HOME){
            HomeScreen()
        }
        composable(MazenRoute.PROFILE){
            ProfileScreen()
        }
        composable(MazenRoute.SETTINGS){
            SettingsScreen()
        }
    }

}
