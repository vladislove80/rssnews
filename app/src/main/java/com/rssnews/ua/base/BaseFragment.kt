package com.rssnews.ua.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import com.rssnews.R
import com.rssnews.data.Categories
import com.rssnews.data.Category
import com.rssnews.data.NewsListItem
import com.rssnews.data.NewsViewModel
import com.rssnews.ua.NewsAdapter
import com.rssnews.ua.categories.CategoriesAdapter
import com.rssnews.util.gone
import com.rssnews.util.visible
import kotlinx.android.synthetic.main.fragment_news.*

/**
 * Created by Vladyslav Ulianytskyi on 28.11.2018.
 */

const val categoriesKey = "category"

open class BaseFragment : Fragment(), BaseHolder.OnItemClickListener<Category> {

    override fun onItemViewClick(view: View, model: Category) {
        when (view.id) {
            R.id.tvCategory -> {
                getCategoriesAdapter().setSelected(model)
                getNewsAdapter().clearItems()
                showProgress()
                NewsViewModel.of(this).getNews(model.link)
            }
        }
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
            val newsAdapter = NewsAdapter(object : BaseHolder.OnItemClickListener<NewsListItem> {
                override fun onItemViewClick(view: View, model: NewsListItem) {
                    //todo
                }
            })
            adapter = newsAdapter
            return newsAdapter
        }
    }

    private fun initCategoriesAdapter(): CategoriesAdapter {
        val categories = arguments?.getParcelable<Categories>(categoriesKey)?.categories ?: mutableListOf()
        rvCategories.apply {
            val categoriesAdapter = CategoriesAdapter(this@BaseFragment)
            (itemAnimator as DefaultItemAnimator).supportsChangeAnimations = false
            categoriesAdapter.addNewItems(categories)
            adapter = categoriesAdapter
            return categoriesAdapter
        }
    }

    fun getCategoriesAdapter() = rvCategories.adapter as? CategoriesAdapter ?: initCategoriesAdapter()

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
}