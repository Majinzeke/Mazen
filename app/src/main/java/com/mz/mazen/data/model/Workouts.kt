package com.mz.mazen.data.model

import androidx.compose.ui.graphics.Color
import com.mz.mazen.R
import com.mz.mazen.ui.theme.NeutralColor

enum class Workouts(
    val icon: Int,
    val contentColor: Color,
    val containerColor: Color
) {
    EmptyPage(
        icon = R.drawable.ic_launcher_background,
        contentColor = Color.White,
        containerColor = NeutralColor
    ),
    Workout(
        icon = R.drawable.training,
        contentColor = Color.Black,
        containerColor = NeutralColor
    ),
    Nutrition(
        icon = R.drawable.nutrition,
        contentColor = Color.Black,
        containerColor = NeutralColor
    ),
    Motivation(
        icon = R.drawable.motivation,
        contentColor = Color.Blue,
        containerColor = NeutralColor
    )
}