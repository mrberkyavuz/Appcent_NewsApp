package com.berkyavuz.newsapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.berkyavuz.newsapp.repository.ArticleRepository
import com.berkyavuz.newsapp.model.entity.ArticleEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val articleRepository: ArticleRepository) : ViewModel() {

    fun getFavoriteArticles() = liveData(Dispatchers.IO) {
        emit(articleRepository.getAllArticles())
    }
}

