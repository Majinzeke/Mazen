package com.mz.mazen.data.model

import com.mz.mazen.data.model.workoutlog_model.WorkoutLogModel

data class PostsFeed(
    val highlightedPost: WorkoutLogModel,
    val recommendedPosts: List<WorkoutLogModel>,
    val popularPosts: List<WorkoutLogModel>,
    val recentPosts: List<WorkoutLogModel>,
) {
    /**
     * Returns a flattened list of all posts contained in the feed.
     */
    val allPosts: List<WorkoutLogModel> =
        listOf(highlightedPost) + recommendedPosts + popularPosts + recentPosts
}