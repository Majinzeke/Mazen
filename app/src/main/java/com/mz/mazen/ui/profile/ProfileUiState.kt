package com.mz.mazen.ui.profile

data class ProfileUiState(
    val userId: String,
    var displayName: String,
    val firstName: String,
    val lastName: String,
    val photo: Int,
    val position: String,
){
    fun isMe() = userId == meProfile.userId
}
