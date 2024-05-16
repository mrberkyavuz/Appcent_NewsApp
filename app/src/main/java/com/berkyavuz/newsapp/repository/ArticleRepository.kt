package com.berkyavuz.newsapp.repository

import com.berkyavuz.newsapp.model.dao.ArticleDao
import com.berkyavuz.newsapp.model.entity.ArticleEntity
import com.berkyavuz.newsapp.model.response.Article
import com.berkyavuz.newsapp.network.service.NewsService

class ArticleRepository(
    private val articleDao: ArticleDao,
    private val newsService: NewsService
) {

    suspend fun getNews(query: String, apiKey: String, page: Int): List<Article> {
        val response = newsService.searchNews(query, apiKey, page)
        if (response.isSuccessful) {
            response.body()?.let {
                return it.articles
            }
        }
        throw Exception("Error fetching news")
    }

    suspend fun getAllArticles(): List<ArticleEntity> {
        return articleDao.getAllArticles()
    }

    suspend fun insertArticle(article: ArticleEntity): Long {
        return articleDao.insertArticle(article)
    }

    suspend fun deleteArticle(article: ArticleEntity): Int {
        return articleDao.deleteArticle(article)
    }
}

