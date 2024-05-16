package com.berkyavuz.newsapp.repository

import com.berkyavuz.newsapp.network.service.NewsService

class NewsRepository(private val apiService: NewsService) {
    suspend fun getNews(query: String, apiKey: String, page: Int) = apiService.searchNews(query, apiKey, page)
}

