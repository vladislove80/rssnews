package com.rssnews.data

import android.util.Log
import com.rssnews.data.api.RssResponse
import com.rssnews.data.model.NewsItem
import com.rssnews.data.source.DataSource
import com.rssnews.data.source.RemoteDataSource

/**
 * Created by Vladyslav Ulianytskyi on 04.12.2018.
 */
class NewsRepository(
    private val remoteDataSource: RemoteDataSource<RssResponse>,
    private val localDataSource: DataSource<List<NewsItem>>
) : DataSource<List<NewsItem>> {

    private var cachedNews: HashMap<String, List<NewsItem>> = HashMap()

    override fun getNews(category: String, link: String, callback: DataSource.Callback<List<NewsItem>>) {
        if (cachedNews.isNotEmpty()) {
            cachedNews[category]?.let {
                callback.onNewsLoaded(it)
                return
            }
        }

        //todo implement DataSourceImpl
        if (true) getNewsFromRemoteDataSource(category, link, callback)
        else getNewsFromLocalDataSource(category, link, callback)
    }

    private fun getNewsFromRemoteDataSource(
        category: String,
        link: String,
        callback: DataSource.Callback<List<NewsItem>>
    ) {
        remoteDataSource.getNews(category, link, object : RemoteDataSource.Callback<RssResponse> {
            override fun onNewsLoaded(news: RssResponse) {
                callback.onNewsLoaded(NewsMapper.rssResponseToItems(news).apply {
                    Log.d(TAG, "getNewsFromRemoteDataSource ${this}")
                    refreshCache(category, this)
                    //todo implement DataSourceImpl
//                    refreshLocalDataSource(category, this)
                })
            }

            override fun onNewsNotAvailable(t: Throwable) {
                Log.d(TAG, "getNewsFromRemoteDataSource $t")
                callback.onNewsNotAvailable(t)
                //todo implement DataSourceImpl
//                getNewsFromLocalDataSource(category, link, callback)
            }
        })
    }

    private fun getNewsFromLocalDataSource(
        category: String,
        link: String,
        callback: DataSource.Callback<List<NewsItem>>
    ) {
        localDataSource.getNews(category, link, callback)
    }


    //todo implement DataSourceImpl
    private fun refreshLocalDataSource(category: String, newsItem: List<NewsItem>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun refreshCache(category: String, news: List<NewsItem>) {
        cachedNews[category] = news
    }

    //todo implement DataSourceImpl
    override fun saveNews(newsItem: NewsItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        private val TAG = "NewsRepository"

        private var INSTANCE: NewsRepository? = null

        @JvmStatic
        fun getInstance(
            remoteDataSource: RemoteDataSource<RssResponse>,
            localDataSource: DataSource<List<NewsItem>>
        ) = INSTANCE
            ?: synchronized(NewsRepository::class.java) {
                INSTANCE ?: NewsRepository(remoteDataSource, localDataSource).also {
                    INSTANCE = it
                }
            }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}