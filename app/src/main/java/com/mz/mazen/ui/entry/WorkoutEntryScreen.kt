package com.mz.mazen.ui.entry

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.mz.mazen.data.model.workoutlog_model.WorkoutLogModel
import com.mz.mazen.data.model.workoutlog_model.WorkoutType
import java.time.ZonedDateTime

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun WorkoutEntryScreen(
    uiState: WorkoutEntryUiState,
    workoutTypeImage: () -> String,
    pagerState: PagerState,
    onTitleChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
    onDeleteConfirmed: () -> Unit,
    onDateTimeUpdated: (ZonedDateTime) -> Unit,
    onBackPressed: () -> Unit,
    onSaveClicked: (WorkoutLogModel) -> Unit
){
    LaunchedEffect(key1 = uiState.workoutType){
        pagerState.scrollToPage(WorkoutType.valueOf(uiState.workoutType.name).ordinal)
    }



    Scaffold(
        topBar = {
            WorkoutEntryTopBar(
                selectedWorkoutEntry = uiState.selectedEntry,
                gameRecordImage = workoutTypeImage,
                onDeleteConfirmed = onDeleteConfirmed,
                onBackPressed = onBackPressed,
            )
        },
        content = {
            uiState.workoutName?.let { it1 ->
                WorkoutLogEntryContent(
                    uiState = uiState,
                    paddingValues = it ,
                    pagerState = pagerState,
                    numberOfSets = uiState.numberOfSets,
                    numberOfReps = uiState.numberOfReps,
                    workoutName = it1,
                    onNumberOfSetsChanged = onDescriptionChanged,
                    onNumberOfRepsChanged =onTitleChanged ,
                    onWorkoutNameChanged =onDescriptionChanged ,
                    onSavedClicked = onSaveClicked
                )
            }
        }
    )
}