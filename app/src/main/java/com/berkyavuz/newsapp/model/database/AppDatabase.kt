package com.berkyavuz.newsapp.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.berkyavuz.newsapp.model.dao.ArticleDao
import com.berkyavuz.newsapp.model.entity.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}
