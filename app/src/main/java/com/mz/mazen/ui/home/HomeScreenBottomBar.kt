package com.mz.mazen.ui.home

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SportsGolf
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.SportsGolf
import androidx.compose.material.icons.outlined.SportsGymnastics
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector

////data class BottomNavigationItem(
//    val title: String,
//    val selectedIcon: ImageVector,
//    val unselectedIcon: ImageVector,
//    val hasNews: Boolean,
//    val badgeCount: Int? = null
//)
//
//val items = listOf(
//    BottomNavigationItem(
//        title = "Home",
//        selectedIcon = Icons.Filled.Home,
//        unselectedIcon = Icons.Outlined.Home,
//        hasNews = false,
//    ),
//    BottomNavigationItem(
//        title = "Profile",
//        selectedIcon = Icons.Filled.Person,
//        unselectedIcon = Icons.Outlined.Person,
//        hasNews = false,
//    ),
//    BottomNavigationItem(
//        title = "WorkoutLog",
//        selectedIcon = Icons.Filled.SportsGymnastics,
//        unselectedIcon = Icons.Outlined.SportsGymnastics,
//        hasNews = false,
//    ),
//    BottomNavigationItem(
//        title = "Etrainer",
//        selectedIcon = Icons.Filled.SportsGolf,
//        unselectedIcon = Icons.Outlined.SportsGolf,
//        hasNews = false,
//    ),
//    BottomNavigationItem(
//        title = "Settings",
//        selectedIcon = Icons.Filled.Settings,
//        unselectedIcon = Icons.Outlined.Settings,
//        hasNews = false,
//    ),
//)
//
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeScreenBottomBar() {
//    var selectedItemIndex by rememberSaveable {
//        mutableIntStateOf(0)
//    }
//    Scaffold(
//        bottomBar = {
//            NavigationBar {
//                items.forEachIndexed { index, item ->
//                    NavigationBarItem(
//                        selected = selectedItemIndex == index,
//                        onClick = {
//                            selectedItemIndex = index
//                        },
//                        label = {
//                                Text(text = item.title)
//                        },
//                        alwaysShowLabel = false,
//                        icon = {
//                            BadgedBox(
//                                badge = {
//                                    if (item.badgeCount != null){
//                                        Badge {
//                                            Text(text = item.badgeCount.toString())
//                                        }
//                                    } else if (item.hasNews){
//                                        Badge()
//                                    }
//                                }
//                            ) {
//                                Icon(
//                                    imageVector = if (index == selectedItemIndex) {
//                                                item.selectedIcon
//                                    }else item.unselectedIcon,
//                                    contentDescription = item.title
//                                )
//                            }
//                        }
//                    )
//                }
//            }
//        }
//    ) {
//
//    }
//}