package com.berkyavuz.newsapp.network.service

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response
import com.berkyavuz.newsapp.model.response.NewsResponse

interface NewsService {
    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = "528d24d42f904de19b0fedcd1408bcf6",
        @Query("page") page: Int = 1
    ): Response<NewsResponse>
}
