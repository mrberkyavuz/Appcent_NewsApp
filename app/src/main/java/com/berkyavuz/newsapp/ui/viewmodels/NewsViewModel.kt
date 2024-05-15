package com.berkyavuz.newsapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.berkyavuz.newsapp.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import com.berkyavuz.newsapp.model.response.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    fun getNews(query: String, apiKey: String, page: Int) = liveData(Dispatchers.IO) {
        try {
            val newsResponse = newsRepository.getNews(query, apiKey, page)
            emit(Result.success(newsResponse.articles))
        } catch (e: Exception) {
            emit(Result.failure<List<Article>>(e))
        }
    }
}

