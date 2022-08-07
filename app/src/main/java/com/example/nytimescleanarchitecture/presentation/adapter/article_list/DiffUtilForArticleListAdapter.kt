package com.example.nytimescleanarchitecture.presentation.adapter.article_list

import androidx.recyclerview.widget.DiffUtil
import com.example.nytimescleanarchitecture.domain.model.PopularArticleDto

class DiffUtilForArticleListAdapter(
    private val oldPopularArticleDtoList: List<PopularArticleDto>,
    private val newPopularArticleDtoList: List<PopularArticleDto>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldPopularArticleDtoList.size
    }

    override fun getNewListSize(): Int {
        return newPopularArticleDtoList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPopularArticleDtoList[oldItemPosition].id == newPopularArticleDtoList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPopularArticleDtoList[oldItemPosition] == newPopularArticleDtoList[newItemPosition]
    }
}