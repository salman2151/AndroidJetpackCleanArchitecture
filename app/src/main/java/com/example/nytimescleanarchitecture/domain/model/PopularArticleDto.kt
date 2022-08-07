package com.example.nytimescleanarchitecture.domain.model

data class PopularArticleDto(
    val id:Long,
    val title: String,
    val description: String?,
    val byLine: String?,
    val url:String
)
