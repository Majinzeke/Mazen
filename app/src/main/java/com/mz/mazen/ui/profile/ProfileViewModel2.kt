package com.mz.mazen.ui.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mz.mazen.R
import com.mz.mazen.data.model.profile_model.ProfileScreenState
import com.mz.mazen.data.model.profile_model.UiState
import com.mz.mazen.utils.Constants

class ProfileViewModel2(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var userId: String = ""

    var uiState by mutableStateOf(ProfileScreenState( firstName = "",
        lastName = "",
        height = 0,
        weight = 0,
        photo = R.drawable.ic_launcher_foreground,
        position = "",
        status = "",
        userId = ""))
        private set

    init {
        getProfileIdArgument()
        setFirstName(firstName = String())
    }

    private fun getProfileIdArgument(){

        uiState = uiState.copy(
            userId  = savedStateHandle.get<String>(
                key = Constants.PROFILE_SCREEN_ARGUMENT_KEY
            )
        )
    }

    fun setFirstName(firstName: String?){
        uiState = uiState.copy(firstName = firstName)
    }
    fun setLastName(lastName: String?){
        uiState = uiState.copy(lastName = lastName)

    }
    fun setHeight(height: Long){
        uiState = uiState.copy(height = height)
    }

    fun setWeight(weight: Long){
        uiState = uiState.copy(weight = weight)
    }




    private val _userData = MutableLiveData<UiState>()
    val userData: LiveData<UiState> = _userData
}

