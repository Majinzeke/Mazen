import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mz.mazen.data.MongoDB
import com.mz.mazen.data.WorkoutEntries
import com.mz.mazen.data.model.RequestState
import com.mz.mazen.ui.entry.WorkoutEntryViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class WorkoutLogViewModel: WorkoutEntryViewModel(savedStateHandle = SavedStateHandle()) {

    var workoutEntries: MutableState<WorkoutEntries> = mutableStateOf(RequestState.Idle)

    init {
        observeAllEntries()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun observeAllEntries(){
        viewModelScope.launch {
            MongoDB.getWorkoutEntries().collect() { result ->
                workoutEntries.value = result
            }
        }
    }
}