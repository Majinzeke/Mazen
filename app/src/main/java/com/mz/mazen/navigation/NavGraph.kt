package com.mz.mazen.navigation

import ProfileScreen
import WorkoutLogScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.camera.core.CameraSelector
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.google.mlkit.vision.pose.Pose
import com.mz.mazen.data.MongoDB
import com.mz.mazen.ui.authentication.AuthenticationScreen
import com.mz.mazen.ui.authentication.AuthenticationViewModel
import com.mz.mazen.ui.etrainer.AiTrainerScreen
import com.mz.mazen.ui.home.HomeScreen
import com.mz.mazen.ui.home.HomeViewModel
import com.mz.mazen.ui.profile.ProfileViewModel2
import com.mz.mazen.ui.workoutlog.WorkoutEntryScreen
import com.mz.mazen.ui.workoutlog.WorkoutLogViewModel
import com.mz.mazen.utils.Constants.PROFILE_SCREEN_ARGUMENT_KEY
import com.stevdzasan.messagebar.rememberMessageBarState
import com.stevdzasan.onetap.rememberOneTapSignInState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String
    ) {

    NavHost(
        navController = navController,
        startDestination = Home.route,
        modifier = modifier
    )
    {
        authenticationRoute(
            navigateToHome = {
                navController.popBackStack()
                navController.navigate(route = Home.route)
            }

        )
        homeRoute(
            navigateToWorkoutLog = {
                navController.navigate(route = WorkoutLog.route)
            },
            navigateToAuth = {
                navController.popBackStack()
                navController.navigate(route = Authentication.route)
            },
            navigateToProfile = {
                navController.navigate(route = Profile.route)
            },

        )
        profileRoute(
            navigateToProfile = {

            },
            navigateToWorkoutLog = {
                navController.navigate(route = WorkoutLog.route)

            },
            navigateToAuth = {

            }
        )
        workoutLogRoute(
            navigateToWorkoutEntryScreen = {
                navController.navigate(route = WorkoutLogEntry.route)

            },
            navigateToWriteWithArgs = {

            }
        )
        workoutEntryScreenRoute(
            navigateToWorkoutLog = {
                navController.navigate(route = WorkoutLog.route)
            },
            paddingValues = PaddingValues(),
            navigateBack = {
                navController.popBackStack()
            }
        )
        aiTrainerRoute(

        )
        settingsRoute()

    }

}

fun NavGraphBuilder.authenticationRoute(navigateToHome: () -> Unit) {

    composable(route = Authentication.route) {
        val viewModel: AuthenticationViewModel = viewModel()
        val loadingState by viewModel.loadingState
        val oneTapState = rememberOneTapSignInState()
        val authenticated by viewModel.authenticated
        val messageBarState = rememberMessageBarState()

        AuthenticationScreen(
            loadingState = loadingState,
            oneTapState = oneTapState,
            messageBarState = messageBarState,
            onButtonClicked = {
                oneTapState.open()
                viewModel.setLoading(true)
            },
            onTokenIdReceived = { tokenId ->
                viewModel.signInWithMongoAtlas(
                    tokenId = tokenId,
                    onSuccess = {
                        messageBarState.addSuccess("successfully Authed")
                        viewModel.setLoading(false)
                    },
                    onError = {
                        messageBarState.addError(it)
                        viewModel.setLoading(false)
                    }
                )
            },
            onDialogDismissed = { message ->
                messageBarState.addError(Exception(message))
                viewModel.setLoading(false)
            },
            authenticated = authenticated,
            navigateToHome = navigateToHome
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.homeRoute(
    navigateToProfile: () -> Unit,
    navigateToWorkoutLog: () -> Unit,
    navigateToAuth: () -> Unit
) {
    composable(route = Home.route) {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen =
            mazenTabScreens.find { it.route == currentDestination?.route }
                ?: Home
        val viewModel: HomeViewModel = viewModel()
        val entries by viewModel.workoutEntries
        var signOutDialogOpened by remember {
            mutableStateOf(false)
        }
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        HomeScreen(
            workoutEntries = entries,
            drawerState = drawerState,
            navigateToProfile = navigateToProfile,
            navigateToWorkoutLog = navigateToWorkoutLog,
            navigateToAuth = navigateToAuth,
        )

        LaunchedEffect(key1 = Unit) {
            MongoDB.configureTheRealm()
        }

    }
}

@OptIn(ExperimentalPagerApi::class)
@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.workoutLogRoute(
    navigateToWorkoutEntryScreen: () -> Unit,
    navigateToWriteWithArgs: (String) -> Unit
) {

    composable(route = WorkoutLog.route) {
        val viewModel: WorkoutLogViewModel = viewModel()
        val entries by viewModel.entries
        val messageBarState = rememberMessageBarState()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val pagerState = rememberPagerState()
        val uiState = viewModel.uiState


        WorkoutLogScreen(
            drawerState = drawerState,
            navigateToWorkoutEntry = navigateToWorkoutEntryScreen,
            navigateToWriteWithArgs = navigateToWriteWithArgs ,
            entries = entries,

        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPagerApi::class)
fun NavGraphBuilder.workoutEntryScreenRoute(
    paddingValues: PaddingValues,
    navigateToWorkoutLog: () -> Unit,
    navigateBack: () -> Unit
){
    composable(route = WorkoutLogEntry.route){
        val viewModel: WorkoutLogViewModel = viewModel()
        val pagerState = rememberPagerState()
        val uiState = viewModel.uiState


        WorkoutEntryScreen(
            pagerState = pagerState,
            workoutType = { "" },
            onSaveClicked = {

            },
            onBackPressed = navigateBack,
            uiState = uiState,
            onDescriptionChanged = {

            },
            onDateTimeUpdated = {

            },
            onDeleteConfirmed = {

            },
            onTitleChanged = {

            }

        )
    }

}

fun NavGraphBuilder.aiTrainerRoute(

) {
    composable(route = AiTrainer.route){
        AiTrainerScreen()
    }
}
@OptIn(ExperimentalPagerApi::class)
@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.settingsRoute(

) {

    composable(route = AiTrainer.route){
        AiTrainerScreen()
    }
}

fun NavGraphBuilder.profileRoute(
    navigateToProfile: () -> Unit,
    navigateToWorkoutLog: () -> Unit,
    navigateToAuth: () -> Unit
) {
    composable(
        route = Profile.route,
        arguments = listOf(navArgument(name = PROFILE_SCREEN_ARGUMENT_KEY){
            type = NavType.StringType
            nullable = true
            defaultValue = null
        })
        ) {
        val viewModel: ProfileViewModel2 = viewModel()
        val uiState = viewModel.uiState

        ProfileScreen(
               uiState = uiState,
               onFirstNameChanged = {viewModel.setFirstName(firstName = it)},
               onLastNameChanged  = {viewModel.setLastName(lastName = it)},
               onWeightChanged = {viewModel.setWeight(weight = 0)},
               onHeightChanged = {viewModel.setHeight(height = 0)},
            onSaveClicked = {
                viewModel.setFirstName(
                    firstName = it.firstName,
                )
                viewModel.setLastName(
                    lastName = it.lastName
                )
                viewModel.setHeight(
                    height = it.height
                )
                viewModel.setWeight(
                    weight = it.weight
                )
            }
           )
    }

}







