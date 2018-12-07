package com.rssnews.data.source

import com.rssnews.data.api.CBCApi
import com.rssnews.data.api.RssResponse
import com.rssnews.data.model.NewsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Vladyslav Ulianytskyi on 05.12.2018.
 */
object RemoteDataSource : NewsDataSource<RssResponse> {
    private const val baseURL = "https://rss.cbc.ca/"
    override fun getNews(category: String, link: String, callback: NewsDataSource.LoadNewsCallback<RssResponse>) {
        CBCApi.create(baseURL).getRssData(link).enqueue(object : Callback<RssResponse> {
            override fun onFailure(call: Call<RssResponse>, t: Throwable) {
                callback.onNewsNotAvailable(t)
            }

            override fun onResponse(call: Call<RssResponse>, response: Response<RssResponse>) {
                if (response.isSuccessful) callback.onNewsLoaded(response.body() ?: RssResponse())
                else callback.onNewsNotAvailable(Throwable("Server not responding"))
            }
        })
    }

    override fun saveNews(newsItem: NewsItem) {
        // Not required for the remote data source.
    }
}