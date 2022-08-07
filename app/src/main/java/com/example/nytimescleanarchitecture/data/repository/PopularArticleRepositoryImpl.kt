package com.example.nytimescleanarchitecture.data.repository

import com.example.nytimescleanarchitecture.data.model.popular_article.PopularArticleResponse
import com.example.nytimescleanarchitecture.data.remote.ApiService
import com.example.nytimescleanarchitecture.domain.repository.PopularArticleRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PopularArticleRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : PopularArticleRepository {
    override suspend fun getMostPopularArticles(): PopularArticleResponse {
        return apiService.getMostPopularArticles()
    }
}