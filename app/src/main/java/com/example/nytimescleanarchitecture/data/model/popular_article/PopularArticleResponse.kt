package com.example.nytimescleanarchitecture.data.model.popular_article

import com.google.gson.annotations.SerializedName


data class PopularArticleResponse(
    @SerializedName("status") var status: String? = null,
    @SerializedName("copyright") var copyright: String? = null,
    @SerializedName("num_results") var numResults: Int? = null,
    @SerializedName("results") var results: ArrayList<PopularArticleResponseDataDto> = arrayListOf()

)