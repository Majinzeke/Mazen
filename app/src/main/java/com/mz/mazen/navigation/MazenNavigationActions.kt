package com.mz.mazen.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.mz.mazen.R

object MazenRoute {
    const val HOME = "Home"
    const val PROFILE = "Profile"
    const val WORKOUTLOG = "WorkoutLog"
    const val SETTINGS = "Settings"
}

data class MazenTopLevelDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int
)

class MazenNavigationActions(private val navController: NavHostController) {

    fun navigateTo(destination: MazenTopLevelDestination) {
        navController.navigate(destination.route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
}

val TOP_LEVEL_DESTINATIONS = listOf(
    MazenTopLevelDestination(
        route = MazenRoute.HOME,
        selectedIcon = Icons.Default.Inbox,
        unselectedIcon = Icons.Default.Inbox,
        iconTextId = R.string.tab_inbox
    ),
    MazenTopLevelDestination(
        route = MazenRoute.PROFILE,
        selectedIcon = Icons.Default.Article,
        unselectedIcon = Icons.Default.Article,
        iconTextId = R.string.tab_article
    ),
    MazenTopLevelDestination(
        route = MazenRoute.WORKOUTLOG,
        selectedIcon = Icons.Outlined.ChatBubbleOutline,
        unselectedIcon = Icons.Outlined.ChatBubbleOutline,
        iconTextId = R.string.tab_inbox
    ),
    MazenTopLevelDestination(
        route = MazenRoute.SETTINGS,
        selectedIcon = Icons.Default.People,
        unselectedIcon = Icons.Default.People,
        iconTextId = R.string.tab_article
    )

)
