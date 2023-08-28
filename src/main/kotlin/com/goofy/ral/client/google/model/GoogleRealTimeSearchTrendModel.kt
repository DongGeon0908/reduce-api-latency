package com.goofy.ral.client.google.model

data class GoogleRealTimeSearchTrendModel(
    val featuredStoryIds: List<String>,
    val trendingStoryIds: List<String>,
    val storySummaries: StorySummary,
    val date: String,
    val hideAllImages: Boolean
) {
    data class Article(
        val articleTitle: String,
        val url: String,
        val source: String,
        val time: String,
        val snippet: String
    )

    data class Image(
        val newsUrl: String,
        val source: String,
        val imgUrl: String
    )

    data class StorySummary(
        val featuredStories: List<Any>,
        val trendingStories: List<TrendingStory>
    )

    data class TrendingStory(
        val image: Image,
        val shareUrl: String,
        val articles: List<Article>,
        val idsForDedup: List<String>,
        val id: String,
        val title: String,
        val entityNames: List<String>
    )
}

