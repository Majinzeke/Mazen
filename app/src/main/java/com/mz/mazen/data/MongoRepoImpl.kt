package com.mz.mazen.data

import com.mz.mazen.data.model.RequestState
import com.mz.mazen.data.model.workoutlog_model.WorkoutLogModel
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId

class MongoRepoImpl(val realm: Realm): MongoRepository{
    override fun configureTheRealm() {
        TODO("Not yet implemented")
    }

    override fun getWorkoutEntries(): Flow<WorkoutEntries> {
        TODO("Not yet implemented")
    }

    override suspend fun getSelectedWorkoutEntry(workoutId: ObjectId): RequestState<WorkoutLogModel> {
        TODO("Not yet implemented")
    }

    override suspend fun addNewWorkoutEntry(selectedWorkoutEntry: WorkoutLogModel): RequestState<WorkoutLogModel> {
        TODO("Not yet implemented")
    }

    override suspend fun loginUserWithEmailAndPassword(selectedWorkoutEntry: WorkoutLogModel): RequestState<WorkoutLogModel> {
        TODO("Not yet implemented")
    }

}