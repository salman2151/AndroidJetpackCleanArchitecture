package com.example.nytimescleanarchitecture.presentation.adapter.article_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nytimescleanarchitecture.databinding.ItemArticleBinding
import com.example.nytimescleanarchitecture.domain.model.PopularArticleDto

class ArticleListAdapter(
    private val list: MutableList<PopularArticleDto>,
    private val onItemClick: (PopularArticleDto) -> Unit
) :
    RecyclerView.Adapter<ArticleListAdapter.ArticleListViewHolder>() {

    fun getList() = list
    fun setUserList(updatedPopularArticleDtoList: List<PopularArticleDto>) {
        val diffResult = DiffUtil.calculateDiff(
            DiffUtilForArticleListAdapter(
                list,
                updatedPopularArticleDtoList
            )
        )
        list.clear()
        list.addAll(updatedPopularArticleDtoList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleListViewHolder {
        return ArticleListViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class ArticleListViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(popularArticleDto: PopularArticleDto) {
            binding.popularArticleDto = popularArticleDto
            binding.root.setOnClickListener {
                onItemClick.run {
                    this(popularArticleDto)
                }
            }
        }

    }
}