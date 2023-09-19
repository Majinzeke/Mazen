package com.mz.mazen.data.model

import androidx.annotation.DrawableRes

data class WorkoutPost(
    val id: String,
    val exerciseName: String,
    val upperBodyWorkoutName: String,
    val lowerBodyworkoutName: String,
    val coreWorkoutName: String,
    val numberOfSets: Int,
    val numberOfReps: Int,
    @DrawableRes val imageId: Int,
)

data class UpperBodyWorkoutName(
    val exerciseName: String,
    val numberOfSets: Int,
    val numberOfReps: Int,
)

data class LowerBodyworkoutName(
    val exerciseName: String,
    val numberOfSets: Int,
    val numberOfReps: Int,
)

data class CoreWorkoutName(
    val exerciseName: String,
    val numberOfSets: Int,
    val numberOfReps: Int,
)
