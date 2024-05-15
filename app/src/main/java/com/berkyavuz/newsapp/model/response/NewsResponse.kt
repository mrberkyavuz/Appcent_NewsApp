package com.berkyavuz.newsapp.model.response

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)
