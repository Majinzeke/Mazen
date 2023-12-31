package com.mz.mazen.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument


interface MazenNavigationActions {
    val icon: ImageVector
    val route:String
}

object Authentication : MazenNavigationActions {
    override val icon = Icons.Filled.AutoAwesome
    override val route = "auth"
}object Home : MazenNavigationActions {
    override val icon = Icons.Filled.Home
    override val route = "home"
}
object Profile : MazenNavigationActions {
    override val icon = Icons.Filled.Person
    override val route = "profile"
}

object AiTrainer : MazenNavigationActions {
    override val icon = Icons.Filled.Computer
    override val route = "aitrainer"
}
object WorkoutLog : MazenNavigationActions {
    override val icon = Icons.Filled.SportsGymnastics
    override val route = "workout_log"
}

object WorkoutLogEntry : MazenNavigationActions {
    override val icon = Icons.Filled.SportsGymnastics
    override val route = "workout_log_entry"
    const val workoutTypeArg = "workout_type"
    val arguments = listOf(
        navArgument(workoutTypeArg) {type= NavType.StringType}
    )
}


fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }

val mazenTabScreens = listOf(
    Authentication,
    Home,
    Profile,
    WorkoutLog,
    AiTrainer
)
