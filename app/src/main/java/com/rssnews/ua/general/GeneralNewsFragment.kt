package com.rssnews.ua.general

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.rssnews.data.model.Categories
import com.rssnews.data.model.NewsItem
import com.rssnews.data.NewsViewModel
import com.rssnews.ua.base.BaseFragment
import com.rssnews.ua.base.categoriesKey
import kotlinx.android.synthetic.main.fragment_news.*

/**
 * Created by Vladyslav Ulianytskyi on 29.11.2018.
 */
class GeneralNewsFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated ")
        val link = arguments?.getParcelable<Categories>(categoriesKey)?.categories!![0].link
        NewsViewModel.of(this).getNews(link)
        rvNews.addOnScrollListener(onScrollListener)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        NewsViewModel.of(this).apply {
            newsLiveData.observe(this@GeneralNewsFragment, Observer<MutableList<NewsItem>> {
                getNewsAdapter().clearItems()
                getNewsAdapter().addNewItems(it)
                showContent()
            })
            errorLiveData.observe(this@GeneralNewsFragment, Observer<Throwable> {
                Log.d(TAG, "errorLiveData $it")
                showRetry()
            })
        }
    }

    companion object {

        private val TAG = GeneralNewsFragment::class.java.simpleName
        fun newInstance(categories: Categories) = GeneralNewsFragment().apply {
            arguments = Bundle().apply { putParcelable(categoriesKey, categories) }
        }

    }
}

private val GeneralNewsFragment.onScrollListener: RecyclerView.OnScrollListener
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
