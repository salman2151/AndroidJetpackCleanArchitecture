package com.example.nytimescleanarchitecture.presentation.fragment.article

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.nytimescleanarchitecture.R
import com.example.nytimescleanarchitecture.databinding.ArticleListFragmentBinding
import com.example.nytimescleanarchitecture.domain.model.PopularArticleDto
import com.example.nytimescleanarchitecture.presentation.adapter.article_list.ArticleListAdapter
import com.example.nytimescleanarchitecture.presentation.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ArticleListFragment : BaseFragment() {

    private var binding: ArticleListFragmentBinding? = null

    private val viewModel: ArticleListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun onItemClickCallback(popularArticleDto: PopularArticleDto) {
        //Toast.makeText(context, popularArticleDto.title, Toast.LENGTH_SHORT).show()
        findNavController().navigate(ArticleListFragmentDirections.actionArticleListFragmentToEmptyFragment())
    }

    private fun setAdapter(list: List<PopularArticleDto>) {
        binding?.rcvArticles?.adapter =
            ArticleListAdapter(list.toMutableList(), onItemClick = ::onItemClickCallback)
    }

    private fun setViewVisibility(
        pbLoadingVisibility: Int = View.GONE,
        tvtErrorVisibility: Int = View.GONE,
        rcvArticlesVisibility: Int = View.GONE
    ) {
        binding?.pbLoading?.visibility = pbLoadingVisibility
        binding?.tvError?.visibility = tvtErrorVisibility
        binding?.rcvArticles?.visibility = rcvArticlesVisibility

    }

    override val layoutId: Int
        get() = R.layout.article_list_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DataBindingUtil.bind(view)
        binding?.let {
            it.lifecycleOwner = this
            setUpView()
        }
    }

    private fun setUpView() {
        lifecycleScope.launchWhenResumed {
            viewModel.msfArticleListState.collectLatest { data ->
                when {
                    data.isLoading -> {
                        setViewVisibility(pbLoadingVisibility = View.VISIBLE)
                    }
                    data.error.isNotEmpty() -> {
                        setViewVisibility(tvtErrorVisibility = View.VISIBLE)
                        binding?.tvError?.text = data.error
                    }
                    else -> {
                        setViewVisibility(rcvArticlesVisibility = View.VISIBLE)
                        setAdapter(data.list)
                    }
                }
            }
        }
    }
}