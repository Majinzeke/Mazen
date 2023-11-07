package com.mz.mazen.data.model.article_lists

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mz.mazen.data.model.home_models.WebsitesLinks
import com.mz.mazen.ui.home.WorkoutCard

@Composable
fun WebsiteList(websiteList: List<WebsitesLinks>, modifier: Modifier = Modifier) {
    LazyRow(modifier = modifier) {
        items(websiteList) { website ->
            WorkoutCard(
                websitesLinks = website,
                modifier = Modifier.padding(8.dp)
            )
        }
    }

}
