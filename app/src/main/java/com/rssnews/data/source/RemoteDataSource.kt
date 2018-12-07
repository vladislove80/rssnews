package com.rssnews.data.source

/**
 * Created by Vladyslav Ulianytskyi on 07.12.2018.
 */
interface RemoteDataSource<T> {

    interface Callback<T> {

        fun onNewsLoaded(news: T)

        fun onNewsNotAvailable(t: Throwable)
    }

    fun getNews(category: String, link: String, callback: Callback<T>)
}