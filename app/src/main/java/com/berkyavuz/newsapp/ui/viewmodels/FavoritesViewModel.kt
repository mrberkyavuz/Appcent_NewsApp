package com.berkyavuz.newsapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.ViewModelProvider
import com.berkyavuz.newsapp.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers

class FavoritesViewModel(private val articleRepository: ArticleRepository) : ViewModel() {

    fun getFavoriteArticles() = liveData(Dispatchers.IO) {
        emit(articleRepository.getAllArticles())
    }

    class Factory(private val articleRepository: ArticleRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FavoritesViewModel(articleRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}




