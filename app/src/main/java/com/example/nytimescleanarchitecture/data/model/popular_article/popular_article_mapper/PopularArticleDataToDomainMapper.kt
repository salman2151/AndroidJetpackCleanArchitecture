package com.example.nytimescleanarchitecture.data.model.popular_article.popular_article_mapper

import com.example.nytimescleanarchitecture.data.model.popular_article.PopularArticleResponseDataDto
import com.example.nytimescleanarchitecture.domain.model.PopularArticleDto
import com.example.nytimescleanarchitecture.domain.model.mapper.DataToDomainMapper

class PopularArticleDataToDomainMapper :
    DataToDomainMapper<PopularArticleResponseDataDto, PopularArticleDto> {

    override fun mapDataToDomainObject(obj: PopularArticleResponseDataDto): PopularArticleDto {
        return PopularArticleDto(
            id = obj.id,
            title = obj.title ?: "No Title",
            description = obj.abstract ?: "No Description",
            byLine = obj.byline ?: "No by line",
            url = obj.url ?: ""
        )
    }
}
