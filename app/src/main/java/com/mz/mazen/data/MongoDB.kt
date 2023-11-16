package com.mz.mazen.data

import android.os.Build
import android.provider.UserDictionary.Words.APP_ID
import android.security.keystore.UserNotAuthenticatedException
import android.util.Log
import androidx.annotation.RequiresApi
import com.mz.mazen.data.model.RequestState
import com.mz.mazen.data.model.workoutlog_model.WorkoutLogModel
import com.mz.mazen.utils.toInstant
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import io.realm.kotlin.query.Sort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.BsonObjectId
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
object MongoDB : MongoRepository {

    private val app = App.create(APP_ID)
    private val user = app.currentUser
    private lateinit var realm: Realm

    init {
        configureTheRealm()
    }



    @RequiresApi(Build.VERSION_CODES.O)
    override fun configureTheRealm() {
        if (user != null) {
            val config = SyncConfiguration.Builder(user, setOf(WorkoutLogModel::class))
                .initialSubscriptions{sub ->
                    add(
                        query = sub.query<WorkoutLogModel>(query = "ownerId == $0", user.id),
                        name = "User Workout Entries"
                    )
                }
                .log(LogLevel.ALL)
                .build()
            realm = Realm.open(config)
        }
    }


    override fun getWorkoutEntries(): Flow<WorkoutEntries> {
        return if (user != null) {
            try {
                realm.query<WorkoutLogModel>(query = "ownerId == $0", user.id)
                    .sort(property = "date", sortOrder = Sort.DESCENDING)
                    .asFlow()
                    .map { result ->
                        RequestState.Success(
                            data = result.list.groupBy {
                                it.date.toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate()
                            }
                        )
                    }
            } catch (e: Exception) {
                flow { emit(RequestState.Error(e)) }
            }
        } else {
            flow { emit(RequestState.Error(UserNotAuthenticatedException())) }
        }


    }

    override suspend fun getSelectedWorkoutEntry(workoutId: BsonObjectId): RequestState<WorkoutLogModel> {
        return if (user != null) {
            try {
                val entry =
                    realm.query<WorkoutLogModel>(query = "_id == $0", workoutId).find().first()
                RequestState.Success(data = entry)
            } catch (e: Exception) {
                RequestState.Error(e)
            }
        } else {
            RequestState.Error(UserNotAuthenticatedException())
        }
    }

    override suspend fun addNewWorkoutEntry(selectedWorkoutEntry: WorkoutLogModel): RequestState<WorkoutLogModel> {
        return if (user != null) {
            realm.write {
                try {
                    val addedEntry = copyToRealm(selectedWorkoutEntry.apply { workoutName = user.id })
                    RequestState.Success(data = addedEntry)
                } catch (e: Exception) {
                    RequestState.Error(e)
                }
            }
        } else {
            RequestState.Error(UserNotAuthenticatedException())
        }
    }

   /// override suspend fun addProfileData(newProfileData: Profile){
    //        return if (user != null){
    //            try {
    //                realm.write {
    //                    val updatedFirstName = copyToRealm(newProfileData.apply {firstName = user.id})
    //                }
    //                realm.write {
    //                    val updatedLastName = copyToRealm(newProfileData.apply { lastName = user.id })
    //                }
    //                realm.write {
    //                    val updatedLastName = copyToRealm(newProfileData.apply { height  })
    //                }
    //                realm.write {
    //                    val updatedLastName = copyToRealm(newProfileData.apply { weight })
    //                }
    //
    //            }catch ()
    //        }else{
    //
    //        }
    //    }

    override suspend fun loginUserWithEmailAndPassword(selectedWorkoutEntry: WorkoutLogModel): RequestState<WorkoutLogModel> {



        return try {
            val email = "<email>"
            val password = "<password>"
            val emailPasswordCredentials: Credentials = Credentials.emailPassword(email, password)

            val user = app.login(emailPasswordCredentials)

            // You can now use the "user" object to access the authenticated user.

            RequestState.Success(selectedWorkoutEntry) // Replace with the appropriate success result
        } catch (e: Exception) {
            Log.e("AUTH", "Error logging in: ${e.message}")
            RequestState.Error(e) // Replace with the appropriate error result
        }
    }

}