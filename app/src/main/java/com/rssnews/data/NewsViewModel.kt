package com.rssnews.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rssnews.data.model.NewsItem
import com.rssnews.data.source.NewsDataSource

/**
 * Created by Vladyslav Ulianytskyi on 30.11.2018.
 */
class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    val newsLiveData: MutableLiveData<List<NewsItem>> = MutableLiveData()
    val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()

    fun getNews(category: String, link: String) =
        newsRepository.getNews(category, link, object : NewsDataSource.LoadNewsCallback<List<NewsItem>> {
            override fun onNewsNotAvailable(t: Throwable) {
                errorLiveData.postValue(t)
            }

            override fun onNewsLoaded(news: List<NewsItem>) {
                newsLiveData.postValue(news)
            }

        })
}