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
import com.mz.mazen.data.model.workoutlog_model.WorkoutType
import com.mz.mazen.utils.Constants.WORKOUTLOG_SCREEN_ARGUMENT_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.mongodb.kbson.ObjectId

@RequiresApi(Build.VERSION_CODES.O)
class WorkoutLogViewModel(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var entries: MutableState<WorkoutEntries> = mutableStateOf(RequestState.Idle)


    var uiState by mutableStateOf(WorkoutEntryUiState())
        private set

    init {
        getEntryIdArgument()
        fetchSelectedEntry()
    }

    private fun getEntryIdArgument(){

        uiState = uiState.copy(
            selectedEntryId = savedStateHandle.get<String>(
                key = WORKOUTLOG_SCREEN_ARGUMENT_KEY
            )
        )
    }

    private fun fetchSelectedEntry(){
        if (uiState.selectedEntryId != null){
            viewModelScope.launch(Dispatchers.Main){
                val entry = MongoDB.getSelectedWorkoutEntry(workoutId = ObjectId.invoke())
                if (entry is RequestState.Success){
                    setSelectedEntry(workoutLogModel = entry.data)
                    setExerciseName(exerciseName = entry.data.exerciseName)
                    setExerciseReps(numberOfReps = entry.data.numberOfReps.toLong())
                    setExerciseSets(numberOfSets = entry.data.numberOfSets.toLong())

                }
            }
        }
    }

    private fun setSelectedEntry(workoutLogModel: WorkoutLogModel){
        uiState = uiState.copy(selectedEntry = workoutLogModel)
    }

    private fun setExerciseName(exerciseName: String){
        uiState = uiState.copy(workoutName = exerciseName )
    }
    private fun setExerciseReps(numberOfReps: Long){
        uiState = uiState.copy(numberOfReps = numberOfReps )
    }
    private fun setExerciseSets(numberOfSets: Long){
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
data class WorkoutEntryUiState(
    val selectedEntryId: String? = null,
    val selectedEntry: WorkoutLogModel? = null,
    val workoutName: String = "",
    val description: String = "",
    val workoutType: WorkoutType = WorkoutType.EmptyWorkout,
    val numberOfSets: Long? = null,
    val numberOfReps: Long? = null
)