package com.rssnews.ui.fragment.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import com.rssnews.R
import com.rssnews.data.model.Categories
import com.rssnews.data.model.Category
import com.rssnews.data.model.NewsItem
import com.rssnews.ui.fragment.category.NewsCategoriesAdapter
import com.rssnews.ui.fragment.news.NewsAdapter
import com.rssnews.ui.viewmodel.NewsViewModel
import com.rssnews.ui.webViewActivityIntent
import com.rssnews.util.gone
import com.rssnews.util.obtainViewModel
import com.rssnews.util.visible
import kotlinx.android.synthetic.main.fragment_news.*

/**
 * Created by Vladyslav Ulianytskyi on 28.11.2018.
 */

const val CATEGORIES_KEY = "category"

open class BaseFragment : Fragment(), BaseHolder.OnItemClickListener<Category> {

    lateinit var viewModel: NewsViewModel

    override fun onItemViewClick(view: View, model: Category) {
        when (view.id) {
            R.id.tvCategory -> {
                getCategoriesAdapter().setSelection(model)
                getNewsAdapter().clearItems()
                showProgress()
                viewModel.getNews(model)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("BaseFragment", "onCreate")
        viewModel = obtainViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRetry.setOnClickListener {
            //todo implement retry get data
        }
        initCategoriesAdapter()
        initNewsAdapter()
    }

    private fun initNewsAdapter(): NewsAdapter {
        rvNews.apply {
            val newsAdapter = NewsAdapter(object : BaseHolder.OnItemClickListener<NewsItem> {
                override fun onItemViewClick(view: View, model: NewsItem) {
                    startActivity(context.webViewActivityIntent(model.newsLink))
                }
            })
            adapter = newsAdapter
            return newsAdapter
        }
    }

    private fun initCategoriesAdapter(): NewsCategoriesAdapter {
        val categories = arguments?.getParcelable<Categories>(CATEGORIES_KEY)?.categories
                ?: mutableListOf()
        rvCategories.apply {
            val newsCategoriesAdapter = NewsCategoriesAdapter(this@BaseFragment)
            (itemAnimator as DefaultItemAnimator).supportsChangeAnimations = false
            newsCategoriesAdapter.addNewItems(categories)
            adapter = newsCategoriesAdapter
            return newsCategoriesAdapter
        }
    }

    fun getCategoriesAdapter() = rvCategories.adapter as? NewsCategoriesAdapter
            ?: initCategoriesAdapter()

    fun getNewsAdapter() = rvNews.adapter as? NewsAdapter ?: initNewsAdapter()

    fun showProgress() {
//        rvNews.gone()
        btnRetry.gone()
//        rvCategories.gone()
        progressBar.visible()
    }

    fun showContent() {
        rvNews.visible()
        rvCategories.visible()
        btnRetry.gone()
        progressBar.gone()
    }

    fun showRetry() {
        rvNews.gone()
//        rvCategories.gone()
        btnRetry.visible()
        progressBar.gone()
    }

    private fun obtainViewModel(): NewsViewModel = obtainViewModel(NewsViewModel::class.java)
}