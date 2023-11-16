package com.mz.mazen.data.model.workoutlog_model

import android.os.Build
import androidx.annotation.RequiresApi
import com.mz.mazen.utils.toRealmInstant
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId
import java.time.Instant

@RequiresApi(Build.VERSION_CODES.O)
open class WorkoutLogModel: RealmObject{
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var workoutName: String = ""
    var isSelected: Boolean = false
    var exerciseName: String = ""
    var numberOfReps: Int = 0
    var numberOfSets: Int = 0
    var rateOfPerceivedExertion: Int = 2
    var date: RealmInstant = Instant.now().toRealmInstant()
}





