package com.berkyavuz.newsapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.ViewModelProvider
import com.berkyavuz.newsapp.repository.ArticleRepository
import com.berkyavuz.newsapp.model.response.Article
import kotlinx.coroutines.Dispatchers

class NewsViewModel(private val articleRepository: ArticleRepository) : ViewModel() {

    fun getNews(query: String, apiKey: String, page: Int) = liveData(Dispatchers.IO) {
        try {
            emit(Result.success(articleRepository.getNews(query, apiKey, page)))
        } catch (e: Exception) {
            emit(Result.failure<List<Article>>(e))
        }
    }

    class Factory(private val articleRepository: ArticleRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NewsViewModel(articleRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}