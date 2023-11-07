package com.mz.mazen.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.filled.MonitorWeight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mz.mazen.R
import com.mz.mazen.utils.baselineHeight

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ProfileScreen(
    uiState: UiState,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onHeightChanged: (String) -> Unit,
    onWeightChanged: (String) -> Unit,
    nestedScrollInteropConnection: NestedScrollConnection = rememberNestedScrollInteropConnection()
) {
    var functionalityNotAvailablePopupShown by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollInteropConnection)
            .systemBarsPadding()
    ) {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
            ) {
                ProfileHeader(
                    scrollState,
                    uiState,
                    this@BoxWithConstraints.maxHeight
                )
                UserInfoFields(uiState, this@BoxWithConstraints.maxHeight)
            }
        }
    }
}

@Composable
private fun UserInfoFields(uiState: UiState, containerHeight: Dp) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))

        NameAndPosition(uiState)

        // Add a spacer that always shows part (320.dp) of the fields list regardless of the device,
        // in order to always leave some content at the top.
        Spacer(Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}

@Composable
private fun NameAndPosition(
    uiState: UiState
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        UserInputText()
        Divider()
        Position(
            uiState,
            modifier = Modifier
                .padding(bottom = 20.dp)
                .baselineHeight(24.dp)
        )
    }
}


@Composable
private fun Position(uiState: UiState, modifier: Modifier = Modifier) {
    uiState.firstName?.let {
        Text(
        text = it,
        modifier = modifier,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
    }
    uiState.lastName?.let {
        Text(
        text = it,
        modifier = modifier,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
    }
    uiState.height?.let {
        Text(
        text = it,
        modifier = modifier,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
    }
    uiState.weight?.let {
        Text(
        text = it,
        modifier = modifier,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
    }
}

@Composable
private fun ProfileHeader(
    scrollState: ScrollState,
    data: UiState,
    containerHeight: Dp
) {
    val offset = (scrollState.value / 2)
    val offsetDp = with(LocalDensity.current) { offset.toDp() }

    data.photo?.let {
        Image(
            modifier = Modifier
                .heightIn(max = containerHeight / 2)
                .fillMaxWidth()
                // TODO: Update to use offset to avoid recomposition
                .padding(
                    start = 16.dp,
                    top = offsetDp,
                    end = 16.dp
                )
                .clip(CircleShape),
            painter = painterResource(id = it),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }
}

@Composable
private fun UserInputText() {
    var firstNameText by remember { mutableStateOf(TextFieldValue("")) }
    var lastNameText by remember { mutableStateOf(TextFieldValue("")) }
    var heightText by remember { mutableStateOf(TextFieldValue("")) }
    var weightText by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = firstNameText,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "first name"
            )
        },
        onValueChange = {
            firstNameText = it
        },
        label = {
            Text(text = "First Name")
        },
        placeholder = {
            Text(text = "Enter your first name.")
        }

    )
    Button(onClick = { /*TODO*/ }) {
        
    }
    OutlinedTextField(
        value = lastNameText,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person2,
                contentDescription = "last name"
            )
        },
        onValueChange = {
            lastNameText = it
        },
        label = {
            Text(text = "last Name")
        },
        placeholder = {
            Text(text = "Enter your last name.")
        }

    )
    OutlinedTextField(
        value = heightText,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Height,
                contentDescription = "height"
            )
        },
        onValueChange = {
            heightText = it
        },
        label = {
            Text(text = "height")
        },
        placeholder = {
            Text(text = "Enter your height.")
        }

    )
    OutlinedTextField(
        value = weightText,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.MonitorWeight,
                contentDescription = "weight"
            )
        },
        onValueChange = {
            weightText = it
        },
        label = {
            Text(text = "weight")
        },
        placeholder = {
            Text(text = "Enter your weight.")
        }
    )
}