package com.rssnews.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rssnews.data.model.Category
import com.rssnews.data.model.NewsItem
import com.rssnews.data.source.DataSource

/**
 * Created by Vladyslav Ulianytskyi on 30.11.2018.
 */
class NewsViewModel(private val newsRepository: DataSource<List<NewsItem>>) : ViewModel() {

    val newsLiveData: MutableLiveData<List<NewsItem>> = MutableLiveData()
    val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()
    private var lastRequestedCategory: Category? = null

    fun getNews(category: Category) {
        this.lastRequestedCategory = category
        newsRepository.getNews(category.categoryName, category.link, object : DataSource.Callback<List<NewsItem>> {
            override fun onNewsNotAvailable(t: Throwable) {
                errorLiveData.postValue(t)
            }

            override fun onNewsLoaded(news: List<NewsItem>) {
                newsLiveData.postValue(news)
            }
        })
    }

    fun retryGetNews() {
        val category = lastRequestedCategory
        if (category != null) {
            getNews(category)
        }
    }
}