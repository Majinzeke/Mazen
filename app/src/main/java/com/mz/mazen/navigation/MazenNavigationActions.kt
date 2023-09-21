package com.mz.mazen.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.mz.mazen.R


interface MazenNavigationActions {
    val icon: ImageVector
    val route:String
}

object Home : MazenNavigationActions {
    override val icon = Icons.Filled.Home
    override val route = "home"
}
object Profile : MazenNavigationActions {
    override val icon = Icons.Filled.Person
    override val route = "profile"
}
object Settings : MazenNavigationActions {
    override val icon = Icons.Filled.Settings
    override val route = "settings"
}
object WorkoutLog : MazenNavigationActions {
    override val icon = Icons.Filled.SportsGymnastics
    override val route = "settings"
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

val mazenTabRowScreens = listOf(Home, Profile, Settings,WorkoutLog)
