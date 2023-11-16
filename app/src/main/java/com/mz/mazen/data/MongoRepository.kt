package com.mz.mazen.data

import com.mz.mazen.data.model.RequestState
import com.mz.mazen.data.model.workoutlog_model.WorkoutLogModel
import com.mz.mazen.ui.workoutlog.WorkoutLogUiState
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId
import java.time.LocalDate

typealias WorkoutEntries = RequestState<Map<LocalDate, List<WorkoutLogModel>>>

interface MongoRepository {
    fun configureTheRealm()
    fun getWorkoutEntries(): Flow<WorkoutEntries>
    suspend fun getSelectedWorkoutEntry(workoutId: ObjectId): RequestState<WorkoutLogModel>
    suspend fun addNewWorkoutEntry(selectedWorkoutEntry: WorkoutLogModel): RequestState<WorkoutLogModel>
    suspend fun loginUserWithEmailAndPassword(selectedWorkoutEntry: WorkoutLogModel): RequestState<WorkoutLogModel>
}