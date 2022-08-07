package com.example.nytimescleanarchitecture.domain.use_case

import com.example.nytimescleanarchitecture.data.model.popular_article.popular_article_mapper.PopularArticleDataToDomainMapper
import com.example.nytimescleanarchitecture.data.network.NetworkResponse
import com.example.nytimescleanarchitecture.domain.model.PopularArticleDto
import com.example.nytimescleanarchitecture.domain.repository.PopularArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMostPopularArticlesListUseCase @Inject constructor(private val popularArticleRepository: PopularArticleRepository) {
    operator fun invoke(): Flow<NetworkResponse<List<PopularArticleDto>>> =
        flow {
            try {
                emit(NetworkResponse.Loading())
                val response = popularArticleRepository.getMostViewedArticles()
                val list: List<PopularArticleDto> = if (response.results.isNullOrEmpty().not())
                    response.results.map {
                        PopularArticleDataToDomainMapper().mapDataToDomainObject(it)
                    }
                else
                    emptyList()

                emit(NetworkResponse.Success(data = list))
            } catch (e: HttpException) {
                emit(NetworkResponse.Error(message = e.message() ?: "HttpException"))
            } catch (e: IOException) {
                emit(NetworkResponse.Error(message = e.message ?: "IOException"))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(message = e.message ?: "Exception"))
            }

        }
}