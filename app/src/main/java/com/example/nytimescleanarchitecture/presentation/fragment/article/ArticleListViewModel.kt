package com.example.nytimescleanarchitecture.presentation.fragment.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytimescleanarchitecture.data.network.NetworkResponse
import com.example.nytimescleanarchitecture.domain.use_case.GetMostPopularArticlesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ArticleListViewModel @Inject constructor(private val getMostPopularArticlesListUseCase: GetMostPopularArticlesListUseCase) :
    ViewModel() {

    private val _msfArticleListState = MutableStateFlow(ArticleListState())
    val msfArticleListState = _msfArticleListState.asStateFlow()

    init {
        getPopularArticles()
    }

    private fun getPopularArticles() {
//        viewModelScope.launch {
        getMostPopularArticlesListUseCase().onEach { itNetworkResponseListDomainArticleDto ->
//            viewModelScope.launch {
            when (itNetworkResponseListDomainArticleDto) {
                is NetworkResponse.Loading -> {
                    _msfArticleListState.value = ArticleListState(isLoading = true)
                }
                is NetworkResponse.Error -> {
                    _msfArticleListState.value = ArticleListState(
                        error = itNetworkResponseListDomainArticleDto.message ?: "Error!"
                    )
                }
                is NetworkResponse.Success -> {

                    _msfArticleListState.value =
                        if (itNetworkResponseListDomainArticleDto.data.isNullOrEmpty().not())
                            ArticleListState(
                                list = itNetworkResponseListDomainArticleDto.data!!,
                            )
                        else
                            ArticleListState(
                                error = "Returned list is empty"
                            )
                }
            }
//            }
        }.launchIn(viewModelScope)
    }
}