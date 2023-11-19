package com.mz.mazen.ui.workoutlog

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.mz.mazen.data.model.workoutlog_model.WorkoutLogModel
import com.mz.mazen.data.model.workoutlog_model.WorkoutType
import java.time.ZonedDateTime


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun WorkoutEntryScreen(
    uiState: WorkoutEntryUiState,
    workoutType: () -> String,
    pagerState: PagerState,
    onTitleChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
    onDeleteConfirmed: () -> Unit,
    onDateTimeUpdated: (ZonedDateTime) -> Unit,
    onBackPressed: () -> Unit,
    onSaveClicked: (WorkoutLogModel) -> Unit
) {

    LaunchedEffect(key1 = uiState.workoutType){
        pagerState.scrollToPage(WorkoutType.valueOf(uiState.workoutType.toString()).ordinal)
    }



    Scaffold(
        topBar = {
            WorkoutEntryTopBar(
                selectedWorkoutEntry = uiState.selectedEntry,
                workoutType = workoutType,
                onDeleteConfirmed = onDeleteConfirmed,
                onBackPressed = onBackPressed,
            )
        },
        content = {
            WorkoutLogEntryContent(
                uiState = uiState,
                paddingValues = it ,
                pagerState = pagerState,
                workoutName = uiState.workoutName,
                numberOfReps = uiState.numberOfReps.toString() ,
                numberOfSets = uiState.numberOfSets.toString(),
                onSavedClicked = {

                },
                onWorkoutNameChanged = {

                },
                onNumberOfRepsChanged = {

                },
                onNumberOfSetsChanged = {

                }
            )
        }
    )
}