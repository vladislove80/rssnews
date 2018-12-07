package com.rssnews.data.source

import com.rssnews.data.model.NewsItem

/**
 * Created by Vladyslav Ulianytskyi on 04.12.2018.
 */
interface DataSource<T> {

    interface Callback<T> {

        fun onNewsLoaded(news: T)

        fun onNewsNotAvailable(t: Throwable)
    }

    fun getNews(category: String, link: String, callback: Callback<T>)

    fun saveNews(newsItem: NewsItem)
}