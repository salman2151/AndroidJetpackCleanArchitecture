package com.example.nytimescleanarchitecture.data.remote

import com.example.nytimescleanarchitecture.BuildConfig
import com.example.nytimescleanarchitecture.data.model.popular_article.PopularArticleResponse
import retrofit2.http.GET

interface ApiService {
    @GET("mostpopular/v2/mostviewed/all-sections/7.json?api-key=${BuildConfig.API_KEY}")
    suspend fun getMostPopularArticles(): PopularArticleResponse
}