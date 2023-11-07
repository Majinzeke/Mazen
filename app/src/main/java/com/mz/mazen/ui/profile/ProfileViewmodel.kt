package com.mz.mazen.ui.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mz.mazen.data.model.profile_model.ProfileModel
import com.mz.mazen.data.model.workoutlog_model.UIState
import com.mz.mazen.utils.Constants.PROFILE_SCREEN_ARGUMENT_KEY

class ProfileViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel(){

    private var userId: String = ""

    var uiState by mutableStateOf(UiState())
        private set

    init {
        getProfileIdArgument()
        setFirstName(firstName = String())
    }

    private fun getProfileIdArgument(){

        uiState = uiState.copy(
            selectedProfileId = savedStateHandle.get<String>(
                key = PROFILE_SCREEN_ARGUMENT_KEY
            )
        )
    }

    fun setFirstName(firstName: String){
        uiState = uiState.copy(firstName = firstName)
    }
    fun setLastName(lastName: String?){
        uiState = uiState.copy(lastName = lastName)

    }
    fun setHeight(height: String?){
        uiState = uiState.copy(height = height)
    }

    fun setWeight(weight: String?){
        uiState = uiState.copy(weight = weight)
    }




    private val _userData = MutableLiveData<ProfileUiState>()
    val userData: LiveData<ProfileUiState> = _userData
}

data class UiState(
    val selectedProfileId: String? = null,
    val photo: Int? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val weight: String? = null,
    val height: String? = null,
)

