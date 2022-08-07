package com.example.nytimescleanarchitecture.presentation.fragment.article

import com.example.nytimescleanarchitecture.domain.model.PopularArticleDto

data class ArticleListState(
    val list: List<PopularArticleDto> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false,
) {}
