package com.mz.mazen.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.annotation.concurrent.Immutable

class ProfileViewModel : ViewModel(){

    private var userId: String = ""

    fun setUserId(newUserId: String?){
        if (newUserId != userId){
            userId = newUserId ?: meProfile.userId
        }
        _userData.value = if (userId == meProfile.userId || userId == meProfile.displayName){
            meProfile
        } else{
            otherProfile
        }
    }
    private val _userData = MutableLiveData<ProfileUiState>()
    val userData: LiveData<ProfileUiState> = _userData
}


@Immutable
data class ProfileModel(
    val displayName: String,
    val userId: String,
    val name: String,
    val description: String,
    val star: String,
    val language: String
){
    fun isMe() = userId == meProfile.userId
}

val meProfile = ProfileUiState(
    firstName = "",
    lastName = "",
    userId = "",
    displayName = "",
    position = "",
    photo = 1
)
val otherProfile = ProfileUiState(
    firstName = "",
    lastName = "",
    userId = "",
    displayName = "",
    position = "",
    photo = 1
)

