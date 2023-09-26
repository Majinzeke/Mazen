package com.mz.mazen.ui.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mz.mazen.R
import com.mz.mazen.data.model.FeatureList
import com.mz.mazen.data.model.ImageTextList
import com.mz.mazen.data.model.ProfileModel
import com.mz.mazen.ui.settings.SettingsScreen

const val username = "majinzeke"
const val my_description = "Profile to show progress on workouts and weightloss."
val profilePopularList = listOf(
    ProfileModel("Upper Body Workout", "A collection on upper body workouts", "25", "Kotlin"),
    ProfileModel("Lower Body Workout", "A collection on lower body workouts", "9", "Kotlin"),
    ProfileModel("Core Workout", "A collection on core workouts.", "45", "Kotlin")
)

val imageTextList = listOf(
    ImageTextList(DCodeIcon.ImageVectorIcon(MyIcons.Location),"vista"),
    ImageTextList(DCodeIcon.ImageVectorIcon(MyIcons.Email),"yup@gmail.com"),
    ImageTextList(DCodeIcon.ImageVectorIcon(MyIcons.AccountBox), "hii")
)

val moreOptionsList = listOf(
    FeatureList("Edit Profile", DCodeIcon.ImageVectorIcon(MyIcons.Edit), ""),
    FeatureList("Manage Account", DCodeIcon.ImageVectorIcon(MyIcons.AccountBox), ""),
    FeatureList("Privacy Policy", DCodeIcon.DrawableResourceIcon(MyIcons.Policy), ""),
    FeatureList("About", DCodeIcon.ImageVectorIcon(MyIcons.Info), ""),
    FeatureList("Help & Feedback", DCodeIcon.DrawableResourceIcon(MyIcons.AppIcon), ""),
    FeatureList("Share 'code'", DCodeIcon.ImageVectorIcon(MyIcons.Share), ""),
)


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(onGoBack: () -> Unit) {


    Scaffold(
        modifier = Modifier.semantics {
            testTagsAsResourceId = true
        },
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.txt_profile))
                },
                navigationIcon = {
                    IconButton(onClick = onGoBack) {
                        Icon(MyIcons.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(MyIcons.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = { }) {
                        Icon(MyIcons.MoreVert, contentDescription = "More")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent,
                ),
            )
        }
    ) { padding ->

        ProfileContent(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(padding)
        ) {
            TopProfileLayout()
            MainProfileContent()
            FooterContent()
        }
    }
}

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        content()
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TopProfileLayout() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(8),
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier.padding(vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = DCodeIcon.DrawableResourceIcon(MyIcons.AppIcon).id),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(60.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = username,
                        style = MaterialTheme.typography.labelMedium,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }

            Text(
                modifier = Modifier.padding(vertical = 5.dp),
                text = my_description,
                style = MaterialTheme.typography.bodySmall,
            )

            FlowRow(modifier = Modifier.padding(vertical = 5.dp)) {
                imageTextList.forEach {
                    ImageTextContent(
                        modifier = Modifier.padding(vertical = 5.dp),
                        icon = {
                            when (it.icon) {
                                is DCodeIcon.ImageVectorIcon -> Icon(
                                    imageVector = it.icon.imageVector,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(20.dp)
                                )

                                is DCodeIcon.DrawableResourceIcon -> Icon(
                                    painter = painterResource(id = it.icon.id),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(20.dp)
                                )
                            }
                        },
                        text = {
                            Text(
                                text = it.text,
                                style = MaterialTheme.typography.labelLarge,
                            )
                        }
                    )
                }
            }

        }

    }
}

@Composable
fun ImageTextContent(
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon()
        Spacer(modifier = Modifier.width(5.dp))
        text()
        Spacer(modifier = Modifier.width(10.dp))
    }
}

@Composable
fun MainProfileContent() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(8),
    ) {
        Column(modifier = Modifier.padding(5.dp)) {
            Text(
                modifier = Modifier
                    .padding(10.dp),
                text = "Popular",
                style = MaterialTheme.typography.titleMedium,
            )
            PopularContentList()

            Divider(modifier = Modifier.padding(vertical = 15.dp))

            GitContentItem(
                modifier = Modifier.padding(vertical = 2.dp),
                icon = {
                    Icon(
                        imageVector = DCodeIcon.ImageVectorIcon(MyIcons.List).imageVector,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(6.dp)
                    )
                },
                text = {
                    Text(
                        text = "Repositories",
                        style = MaterialTheme.typography.labelLarge,
                    )
                },
                endItem = {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "24"
                    )
                }
            )
            GitContentItem(
                modifier = Modifier.padding(vertical = 2.dp),
                icon = {
                    Icon(
                        imageVector = DCodeIcon.ImageVectorIcon(MyIcons.Star).imageVector,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(6.dp)
                    )
                },
                text = {
                    Text(
                        text = "Starred",
                        style = MaterialTheme.typography.labelLarge,
                    )
                },
                endItem = {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "60"
                    )
                }
            )
        }
    }
}

@Composable
fun PopularContentList() {
    LazyRow {
        items(
            items = profilePopularList,
            itemContent = {
                Surface(
                    modifier = Modifier
                        .width(250.dp)
                        .padding(5.dp),
                    shape = RoundedCornerShape(8),
                    border = BorderStroke(0.1.dp, MaterialTheme.colorScheme.outline)
                ) {
                    Column(modifier = Modifier.padding(5.dp)) {
                        Row(
                            modifier = Modifier.padding(vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                painter = painterResource(id = DCodeIcon.DrawableResourceIcon(
                                    MyIcons.AppIcon
                                ).id),
                                contentDescription = null,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = it.name,
                                style = MaterialTheme.typography.titleSmall,
                            )
                        }

                        Text(
                            modifier = Modifier.padding(vertical = 5.dp),
                            text = it.description,
                            style = MaterialTheme.typography.bodySmall, maxLines = 2,
                        )

                        Row(
                            modifier = Modifier.padding(vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            ImageTextContent(
                                modifier = Modifier.padding(vertical = 5.dp),
                                icon = {
                                    Icon(
                                        imageVector = DCodeIcon.ImageVectorIcon(MyIcons.Star).imageVector,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .size(15.dp)
                                    )
                                },
                                text = {
                                    Text(
                                        text = it.star,
                                        style = MaterialTheme.typography.labelLarge,
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            ImageTextContent(
                                modifier = Modifier.padding(vertical = 5.dp),
                                icon = {
                                    Icon(
                                        painter = painterResource(id = DCodeIcon.DrawableResourceIcon(
                                            MyIcons.AppIcon
                                        ).id),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .size(10.dp)
                                    )
                                },
                                text = {
                                    Text(
                                        text = it.language,
                                        style = MaterialTheme.typography.labelLarge,
                                    )
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun GitContentItem(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    endItem: @Composable () -> Unit,
) {
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon()
        Column(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .weight(1f)
        ) {
            text()
        }
        endItem()
    }
}

@Composable
fun FooterContent() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(8),
    ) {
        Column(modifier = Modifier.padding(5.dp)) {
            Text(
                modifier = Modifier
                    .padding(10.dp),
                text = stringResource(id = R.string.txt_more_options),
                style = MaterialTheme.typography.titleMedium,
            )
            moreOptionsList.forEach {
                MoreOptionsComp(it)
            }
        }
    }
}

@Composable
fun MoreOptionsComp(
    featureList: FeatureList,
) {
    Row(
        modifier = Modifier.padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        when (featureList.listIcon) {
            is DCodeIcon.ImageVectorIcon -> Icon(
                imageVector = featureList.listIcon.imageVector,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(6.dp)
            )

            is DCodeIcon.DrawableResourceIcon -> Icon(
                painter = painterResource(id = featureList.listIcon.id),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(6.dp)
            )
            else -> {
                SettingsScreen()
            }
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .weight(1f)
        ) {
            Text(
                text = featureList.name,
                style = MaterialTheme.typography.labelLarge
            )
        }
        Icon(
            imageVector = MyIcons.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier.padding(4.dp)
        )
    }
}