package com.rssnews.ua.general

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.rssnews.data.Categories
import com.rssnews.data.NewsListItem
import com.rssnews.data.NewsViewModel
import com.rssnews.ua.base.BaseFragment
import com.rssnews.ua.base.categoriesKey

/**
 * Created by Vladyslav Ulianytskyi on 29.11.2018.
 */
class GeneralNewsFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "response ")
        val link = arguments?.getParcelable<Categories>(categoriesKey)?.categories!![0].link
        NewsViewModel.of(this).getNews(link)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        NewsViewModel.of(this).apply {
            newsLiveData.observe(this@GeneralNewsFragment, Observer<MutableList<NewsListItem>> {
                Log.d(TAG, "newsLiveData $it")
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