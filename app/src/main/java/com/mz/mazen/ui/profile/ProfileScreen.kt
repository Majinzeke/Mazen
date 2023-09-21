package com.mz.mazen.ui.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    var profileName by remember { mutableStateOf("Zalk") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Profile") },
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(Icons.Default.Edit, contentDescription = null)
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                ProfileHeader(name = profileName)
            }
            item {
                ProfileContent()
            }
        }
    }
}

@Composable
fun ProfileHeader(name: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Image
        // Add your profile image composable here

        Spacer(modifier = Modifier.height(16.dp))

        // Profile Name
        Text(
            text = name,
            style = TextStyle(fontSize = 24.sp),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Edit Profile Button
        Button(
            onClick = { /* Handle edit profile button click */ },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Edit Profile")
        }
    }
}

@Composable
fun ProfileContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = "About Me",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "I am a software developer who loves coding and creating amazing apps!",
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Recent Posts",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
            modifier = Modifier.padding(8.dp)
        )

        // Example list of recent posts
        listOf("Post 1", "Post 2", "Post 3").forEach { post ->
            Text(
                text = post,
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}
