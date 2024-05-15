package com.berkyavuz.newsapp.repository

import com.berkyavuz.newsapp.model.dao.ArticleDao
import com.berkyavuz.newsapp.model.entity.ArticleEntity
import javax.inject.Inject

class ArticleRepository @Inject constructor(private val articleDao: ArticleDao) {

    suspend fun insertArticle(article: ArticleEntity) {
        articleDao.insertArticle(article)
    }

    suspend fun deleteArticle(article: ArticleEntity) {
        articleDao.deleteArticle(article)
    }

    suspend fun getAllArticles(): List<ArticleEntity> {
        return articleDao.getAllArticles()
    }
}
