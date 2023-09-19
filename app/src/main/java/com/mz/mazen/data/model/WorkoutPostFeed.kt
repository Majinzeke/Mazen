package com.mz.mazen.data.model

data class PostsFeed(
    val highlightedPost: WorkoutPost,
    val recommendedPosts: List<WorkoutPost>,
    val popularPosts: List<WorkoutPost>,
    val recentPosts: List<WorkoutPost>,
) {
    /**
     * Returns a flattened list of all posts contained in the feed.
     */
    val allPosts: List<WorkoutPost> =
        listOf(highlightedPost) + recommendedPosts + popularPosts + recentPosts
}