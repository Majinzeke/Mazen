package com.mz.mazen.data.model.article_lists

val websiteLists = listOf(
    "https://www.bodybuilding.com/category/training",
    "https://www.bodybuilding.com/category/nutrition",
    "https://www.bodybuilding.com/category/health-and-lifestyle"

).groupBy {
    it.first()
}.toSortedMap()


///val nutritionWebsiteLink = listOf(
//    "https://www.bodybuilding.com/category/nutrition"
//)
//val motivationWebsiteLink = listOf(
//    "https://www.bodybuilding.com/category/health-and-lifestyle"
//)