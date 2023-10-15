package com.mz.mazen


import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.camera.core.CameraSelector
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.mlkit.vision.pose.Pose
import com.mz.mazen.navigation.Home
import com.mz.mazen.navigation.SetupNavGraph
import com.mz.mazen.navigation.mazenTabScreens
import com.mz.mazen.navigation.navigateSingleTopTo
import com.mz.mazen.ui.theme.MazenTheme
import com.mz.mazen.utils.MazenTabRow

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MazenApp() {
    MazenTheme {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        var detectedPose by remember { mutableStateOf<Pose?>(null) }
        val currentDestination = currentBackStack?.destination
        val currentScreen =
            mazenTabScreens.find { it.route == currentDestination?.route }
                ?: Home
        var lens by remember { mutableIntStateOf(CameraSelector.LENS_FACING_FRONT) }
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()


        Scaffold(
            bottomBar = {
                MazenTabRow(
                    allScreens = mazenTabScreens,
                    onTabSelected = { newScreen ->
                        navController.navigateSingleTopTo(newScreen.route)
                    } ,
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding ->
            SetupNavGraph(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )

        }



    }
}
