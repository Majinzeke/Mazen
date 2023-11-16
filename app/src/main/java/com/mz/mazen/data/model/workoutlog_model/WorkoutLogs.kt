package com.mz.mazen.data.model.workoutlog_model

import androidx.compose.ui.graphics.Color
import com.mz.mazen.R


enum class WorkoutLogs(
    val icon: Int,
    val contentColor: Color,
    val containerColor: Color
){
    UpperBodyWorkout(
        icon = R.drawable.ab1_inversions,
        containerColor = Color.Green,
        contentColor = Color.Black
    ),
    LowerBodyWorkout(
        icon = R.drawable.ab2_quick_yoga,
        containerColor = Color.Blue,
        contentColor = Color.Black
    ),
    CoreWorkout(
        icon = R.drawable.ab3_stretching,
        containerColor = Color.Red,
        contentColor = Color.Black
    ),
    FullBodyWorkout(
        icon = R.drawable.ab5_hiit,
        containerColor = Color.Yellow,
        contentColor = Color.Black
    )
}