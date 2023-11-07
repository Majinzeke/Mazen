package com.mz.mazen.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mz.mazen.data.MongoDB
import com.mz.mazen.data.WorkoutEntries
import com.mz.mazen.data.model.RequestState
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)

class HomeViewModel : ViewModel() {

    var workoutEntries: MutableState<WorkoutEntries> = mutableStateOf(RequestState.Idle)

    init {
        observeAllEntries()
    }


    private fun observeAllEntries(){
        viewModelScope.launch {
            MongoDB.getWorkoutEntries().collect() { result ->
                workoutEntries.value = result

            }
        }
    }


}