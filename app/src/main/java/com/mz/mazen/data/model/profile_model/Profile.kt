package com.mz.mazen.data.model.profile_model

import android.os.Build
import androidx.annotation.RequiresApi
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

@RequiresApi(Build.VERSION_CODES.O)
open class ProfileModel: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var firstName: String = ""
    var lastName: String = ""
    var isSelected: Boolean = false
    var email: String = ""
    var height: Long = 0
    var weight: Long = 0


}

