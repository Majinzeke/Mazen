package com.mz.mazen.data.model.workoutlog_model

import androidx.compose.ui.graphics.Color
import com.mz.mazen.R
import com.mz.mazen.ui.theme.NeutralColor


enum class WorkoutType(
    val icon: Int,
    val contentColor: Color,
    val containerColor: Color
){
    EmptyWorkout(
        icon = R.drawable.ic_launcher_background,
        containerColor = Color.Green,
        contentColor = NeutralColor
    )
    ,UpperBodyWorkout(
        icon = R.drawable.ab1_inversions,
        containerColor = Color.Green,
        contentColor = NeutralColor
    ),
    LowerBodyWorkout(
        icon = R.drawable.ab2_quick_yoga,
        containerColor = Color.Blue,
        contentColor = NeutralColor
    ),
    CoreWorkout(
        icon = R.drawable.ab3_stretching,
        containerColor = Color.Red,
        contentColor = NeutralColor
    ),
    FullBodyWorkout(
        icon = R.drawable.ab5_hiit,
        containerColor = Color.Yellow,
        contentColor = NeutralColor
    )
}