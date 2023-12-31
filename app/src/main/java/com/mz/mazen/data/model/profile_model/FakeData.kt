package com.mz.mazen.data.model.profile_model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.mz.mazen.R
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

data class UiState(
    val selectedProfileId: String? = null,
    val photo: Int?,
    val firstName: String?,
    val lastName: String?,
    val weight: Long?,
    val height: Long?,
)


@Immutable
data class ProfileScreenState(
    val userId: String?,
    @DrawableRes val photo: Int?,
    val firstName: String?,
    val lastName: String?,
    val status: String?,
    val height: Long,
    val weight: Long,
    val position: String?,
) {

}

open class Profile: RealmObject {
    @PrimaryKey
    var profileId: ObjectId = ObjectId.invoke()
    var displayName: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var height: Long = 0
    var weight: Long = 0
}

