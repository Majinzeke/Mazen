package com.mz.mazen.data.model

import com.mz.mazen.ui.profile.DCodeIcon

data class ProfileModel(
    val name: String,
    val description: String,
    val star: String,
    val language: String
)

data class ImageTextList(
    val icon: DCodeIcon,
    val text: String
)

