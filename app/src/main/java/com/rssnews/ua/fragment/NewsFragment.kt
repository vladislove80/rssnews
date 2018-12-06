package com.rssnews.ua.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.rssnews.data.model.Categories
import com.rssnews.data.model.NewsItem
import com.rssnews.ua.fragment.base.BaseFragment
import com.rssnews.ua.fragment.base.categoriesKey
import kotlinx.android.synthetic.main.fragment_news.*

/**
 * Created by Vladyslav Ulianytskyi on 29.11.2018.
 */
class NewsFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate ")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val category = arguments?.getParcelable<Categories>(categoriesKey)?.categories?.get(0)
        val link = category?.link ?: ""
        val categoryName = category?.categoryName ?: ""
        viewModel.getNews(categoryName, link)
        rvNews.addOnScrollListener(onScrollListener)
    }

    private fun observeViewModel() {
        viewModel.apply {
            newsLiveData.observe(this@NewsFragment, Observer<List<NewsItem>> {
                getNewsAdapter().clearItems()
                getNewsAdapter().addNewItems(it)
                showContent()
            })
            errorLiveData.observe(this@NewsFragment, Observer<Throwable> {
                Log.d(TAG, "errorLiveData $it")
                showRetry()
            })
        }
    }

    companion object {

        private const val TAG = "NewsFragment"
        fun newInstance(categories: Categories) = NewsFragment().apply {
            arguments = Bundle().apply { putParcelable(categoriesKey, categories) }
        }
    }
}

private val NewsFragment.onScrollListener: RecyclerView.OnScrollListener
    get() {
        return object : RecyclerView.OnScrollListener() {
            var scrollDist = 0
            var isVisible = true
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (isVisible && scrollDist > 25) {
                    rvCategories.animate().translationY(rvCategories.height + 10f).interpolator =
                            AccelerateInterpolator(2f)
                    scrollDist = 0
                    isVisible = false
                } else if (!isVisible && scrollDist < -25) {
                    rvCategories.animate().translationY(0f).interpolator = DecelerateInterpolator(2f)
                    scrollDist = 0
                    isVisible = true
                }

                if ((isVisible && dy > 0) || (!isVisible && dy < 0)) {
                    scrollDist += dy
                }
            }
        }
    }
