package com.mz.mazen.ui.workoutlog

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mz.mazen.data.MongoDB
import com.mz.mazen.data.WorkoutEntries
import com.mz.mazen.data.model.RequestState
import com.mz.mazen.data.model.workoutlog_model.WorkoutLogModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

@RequiresApi(Build.VERSION_CODES.O)
class WorkoutLogViewModel(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var entries: MutableState<WorkoutEntries> = mutableStateOf(RequestState.Idle)

    var uiState by mutableStateOf(WorkoutLogUiState())

    init {
        observeAllEntries()
        fetchSelectedEntry()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun observeAllEntries(){
        viewModelScope.launch {
            MongoDB.getWorkoutEntries().collect() { result ->
                entries.value = result
            }
        }
    }

    private fun fetchSelectedEntry(){
        viewModelScope.launch(Dispatchers.Main){
            val entry = MongoDB.getSelectedWorkoutEntry(workoutId = ObjectId.invoke())
            if (entry is RequestState.Success){
                setSelectedEntry(workoutLogModel = entry.data.workoutName)
                setExerciseName(exerciseName = entry.data.exerciseName)
                setExerciseReps(numberOfReps = entry.data.numberOfReps.toString())
                setExerciseSets(numberOfSets = entry.data.numberOfSets.toString())

            }
        }
    }

    private fun setSelectedEntry(workoutLogModel: String?){
        uiState = uiState.copy(selectedWorkoutId = workoutLogModel)
    }

    private fun setExerciseName(exerciseName: String){
        uiState = uiState.copy(exerciseName = exerciseName )
    }
    private fun setExerciseReps(numberOfReps: String?){
        uiState = uiState.copy(numberOfReps = numberOfReps )
    }
    private fun setExerciseSets(numberOfSets: String?){
        uiState = uiState.copy(numberOfSets = numberOfSets )

    }

    fun insertEntry(
        workoutEntry: WorkoutLogModel,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ){
        viewModelScope.launch(Dispatchers.IO){
            val result = MongoDB.addNewWorkoutEntry(selectedWorkoutEntry = WorkoutLogModel())
            if (result is RequestState.Success){
                withContext(Dispatchers.Main){
                    onSuccess()
                }
            }else if (result is RequestState.Error){
                withContext(Dispatchers.Main){
                    onError(result.error.message.toString())
                }
            }
        }
    }
}


data class WorkoutLogUiState(
    val selectedWorkout: WorkoutLogModel? = null,
    val selectedWorkoutId: String? = null,
    val workoutImage: Int? = null,
    val exerciseName: String = "",
    val numberOfReps: String? = "",
    val numberOfSets: String? = "",
)
