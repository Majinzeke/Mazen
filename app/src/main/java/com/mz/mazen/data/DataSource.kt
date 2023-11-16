package com.mz.mazen.data

import com.mz.mazen.R
import com.mz.mazen.data.model.home_models.WebsitesLinks

class Datasource() {
    fun loadWebsites(): List<WebsitesLinks> {
        return listOf<WebsitesLinks>(
            WebsitesLinks(R.string.workout_article, R.drawable.training),
            WebsitesLinks(R.string.motivation_article, R.drawable.motivation),
            WebsitesLinks(R.string.nutrition_article, R.drawable.nutrition),
        )
    }
}
