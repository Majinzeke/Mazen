package com.mz.mazen.ui.home


import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mz.mazen.R
import com.mz.mazen.data.Datasource
import com.mz.mazen.data.WorkoutEntries
import com.mz.mazen.data.model.article_lists.WebsiteList
import com.mz.mazen.data.model.home_models.WebsitesLinks
import com.mz.mazen.ui.theme.PurpleGrey40

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(text),
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier

) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp)
        ) {
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    Column(


    ) {
        WebsiteList(websiteList = Datasource().loadWebsites())

    }
}

@Composable
fun WorkoutArticles(
    navigateToProfile: () -> Unit,
    scrollState: LazyListState,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    Box(
        modifier = modifier
    ) {

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpenInBrowser() {
    val context = LocalContext.current
    val bodybuildingIntent =
        remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bodybuilding.com")) }
    val nutritionIntent = remember {
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.bodybuilding.com/category/nutrition")
        )
    }

}

@Composable
fun WorkoutCard(websitesLinks: WebsitesLinks, modifier: Modifier = Modifier) {
    val bodybuildingIntent =
        remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bodybuilding.com")) }

    Card (modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        colors = CardDefaults.cardColors(Color.Black),
        shape = CircleShape

    ){
        Column(
            modifier = Modifier
                .padding(7.dp)
                .padding(vertical = 20.dp),
        ) {
            Image(
                painter = painterResource(websitesLinks.imageResourceId),
                contentDescription = stringResource(websitesLinks.stringResourceId),
                modifier = Modifier
                    .padding(horizontal = 60.dp, vertical = 40.dp)
                    .fillMaxSize()
                    .clickable {
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.bodybuilding.com/category/nutrition")
                        )
                    },
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.BottomStart
            )
            Text(
                color = MaterialTheme.colorScheme.onPrimary,
                text = LocalContext.current.getString(websitesLinks.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall,

            )
        }

    }

}

@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(onlineArticleData) { item ->
            FavoriteCollectionCard(item.drawable, item.text,
                Modifier
                    .height(80.dp)
                    .clickable {

                    })
        }
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 30.dp)
                .clickable {

                }
        )
        content()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    workoutEntries : WorkoutEntries,
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    navigateToProfile: () -> Unit,
    navigateToWorkoutLog: () -> Unit,
    navigateToAuth: () -> Unit,

    ) {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    var padding by remember { mutableStateOf(PaddingValues()) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Column(
        modifier
            .verticalScroll(rememberScrollState())

    ) {
        Spacer(Modifier.height(16.dp))
        HomeSection(title = R.string.mazen) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.favorite_collections) {
            FavoriteCollectionsGrid()
        }
        Spacer(Modifier.height(16.dp))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawer(
    drawerState: DrawerState,
    onShowToast: () -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                content = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier.size(250.dp),
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = "Logo Image"
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    NavigationDrawerItem(
                        label = {
                            Row(modifier = Modifier.padding(horizontal = 12.dp)) {
                                Icon(
                                    painterResource(id = R.drawable.ic_launcher_background),
                                    contentDescription = "Profile"
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(text = "Profile")
                            }
                        }, selected = false,
                        onClick = onShowToast
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    NavigationDrawerItem(
                        label = {
                            Row(modifier = Modifier.padding(horizontal = 12.dp)) {
                                Icon(
                                    painterResource(id = R.drawable.ic_launcher_background),
                                    contentDescription = "Workout Log"
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(text = "Workout Log")
                            }
                        }, selected = false,
                        onClick = onShowToast
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    NavigationDrawerItem(
                        label = {
                            Row(modifier = Modifier.padding(horizontal = 12.dp)) {
                                Icon(
                                    painterResource(id = R.drawable.ic_launcher_background),
                                    contentDescription = "Settings"
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(text = "Settings")
                            }
                        }, selected = false,
                        onClick = onShowToast
                    )
                }
            )
        },
        content = content
    )
}

data class Category(
    val name: String,
    val items: List<String>
)

@Composable
private fun CategoryHeader(
    text: String,
    modifier: Modifier = Modifier
) {
    androidx.compose.material.Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)

    )
}

@Composable
private fun CategoryItem(
    text: String,
    modifier: Modifier = Modifier
) {
    androidx.compose.material.Text(
        text = text,
        fontSize = 14.sp,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)

    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CategorizedLazyColumn(
    categories: List<Category>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        categories.forEach { category ->
            stickyHeader {
                CategoryHeader(category.name)
            }
            items(category.items) { text ->
                CategoryItem(text)
            }
        }
    }
}


private val onlineWorkoutData = listOf(

    R.drawable.ab1_inversions to R.string.workout_1,
    R.drawable.ab3_stretching to R.string.workout_2,
    R.drawable.ab4_tabata to R.string.workout_3,
    R.drawable.ab5_hiit to R.string.workout_4


).map { DrawableStringPair(it.first, it.second) }

private val onlineArticleData = listOf(

    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,

    ).map { DrawableStringPair(it.first, it.second) }


private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)
