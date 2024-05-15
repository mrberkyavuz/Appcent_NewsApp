package com.berkyavuz.newsapp.repository

import com.berkyavuz.newsapp.api.NewsApiService
import com.berkyavuz.newsapp.model.response.NewsResponse
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiService: NewsApiService) {
    suspend fun getNews(query: String, apiKey: String, page: Int): NewsResponse {
        return apiService.getNews(query, apiKey, page)
    }
}

