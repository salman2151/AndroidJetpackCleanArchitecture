package com.example.nytimescleanarchitecture.domain.repository

import com.example.nytimescleanarchitecture.data.model.popular_article.PopularArticleResponse

interface PopularArticleRepository {
   suspend fun getMostViewedArticles(): PopularArticleResponse
}