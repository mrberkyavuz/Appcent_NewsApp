package com.berkyavuz.newsapp.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = false)
    val url: String,
    val title: String,
    val description: String,
    val urlToImage: String?,
    val publishedAt: String,
    val source: String
)

