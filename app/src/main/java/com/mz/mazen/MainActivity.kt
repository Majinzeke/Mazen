package com.mz.mazen

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.camera.core.CameraSelector
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.mlkit.vision.pose.Pose
import com.mz.mazen.data.MongoDB
import com.mz.mazen.navigation.Authentication
import com.mz.mazen.navigation.Home
import com.mz.mazen.navigation.SetupNavGraph
import com.mz.mazen.navigation.mazenTabScreens
import com.mz.mazen.navigation.navigateSingleTopTo
import com.mz.mazen.ui.theme.MazenTheme
import com.mz.mazen.utils.Constants.APP_ID
import com.mz.mazen.utils.MazenTabRow
import io.realm.kotlin.mongodb.App


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (permissionGranted()) {
            initView()
        } else {
            requestPermission()
        }

    }

    private fun permissionGranted() =
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.CAMERA), 0
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initView()
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView() {
        setContent {
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

                SetupNavGraph(
                    navController = rememberNavController(),
                    startDestination = getStartDestination(),
                )

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
                        modifier = Modifier.padding(innerPadding),
                         startDestination = getStartDestination()
                    )

                }

            }
        }
    }
}

private fun getStartDestination(): String{ val user = App.create(APP_ID).currentUser
    return if (user != null && user.loggedIn) Home.route
    else Authentication.route}
