package com.berkyavuz.newsapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import com.berkyavuz.newsapp.model.entity.ArticleEntity

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: ArticleEntity): Long

    @Delete
    suspend fun deleteArticle(article: ArticleEntity): Int

    @Query("SELECT * FROM articles")
    suspend fun getAllArticles(): List<ArticleEntity>
}
