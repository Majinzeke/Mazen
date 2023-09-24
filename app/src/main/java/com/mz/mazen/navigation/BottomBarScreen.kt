package com.mz.mazen.navigation

import androidx.annotation.StringRes
import com.mz.mazen.R

sealed class BottomBarScreen(val route: String, @StringRes val resourceId: Int) {
    object Profile : BottomBarScreen("profile", R.string.profile)
    object FriendsList : BottomBarScreen("friendslist", R.string.friends_list)
}
