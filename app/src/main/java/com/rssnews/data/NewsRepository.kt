package com.rssnews.data

import android.util.Log
import com.rssnews.data.api.RssResponse
import com.rssnews.data.model.NewsItem
import com.rssnews.data.source.NewsDataSource

/**
 * Created by Vladyslav Ulianytskyi on 04.12.2018.
 */
class NewsRepository(
    private val remoteDataSource: NewsDataSource<RssResponse>,
    private val localDataSource: NewsDataSource<List<NewsItem>>
) : NewsDataSource<List<NewsItem>> {

    private var cachedNews: HashMap<String, List<NewsItem>> = HashMap()

    override fun getNews(category: String, link: String, callback: NewsDataSource.LoadNewsCallback<List<NewsItem>>) {
        if (cachedNews.isNotEmpty()) {
            cachedNews[category]?.let {
                callback.onNewsLoaded(it)
                return
            }
        }

        //todo implement LocalDataSource
        if (true) getNewsFromRemoteDataSource(category, link, callback)
        else getNewsFromLocalDataSource(category, link, callback)
    }

    private fun getNewsFromRemoteDataSource(
        category: String,
        link: String,
        callback: NewsDataSource.LoadNewsCallback<List<NewsItem>>
    ) {
        remoteDataSource.getNews(category, link, object : NewsDataSource.LoadNewsCallback<RssResponse> {
            override fun onNewsLoaded(news: RssResponse) {
                callback.onNewsLoaded(NewsMapper.rssResponseToItems(news).apply {
                    Log.d(TAG, "getNewsFromRemoteDataSource ${this}")
                    refreshCache(category, this)
                    //todo implement LocalDataSource
//                    refreshLocalDataSource(category, this)
                })
            }

            override fun onNewsNotAvailable(t: Throwable) {
                Log.d(TAG, "getNewsFromRemoteDataSource $t")
                //todo implement LocalDataSource
//                getNewsFromLocalDataSource(category, link, callback)
            }
        })
    }

    private fun getNewsFromLocalDataSource(
        category: String,
        link: String,
        callback: NewsDataSource.LoadNewsCallback<List<NewsItem>>
    ) {
        localDataSource.getNews(category, link, callback)
    }


    //todo implement LocalDataSource
    private fun refreshLocalDataSource(category: String, newsItem: List<NewsItem>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun refreshCache(category: String, news: List<NewsItem>) {
        cachedNews[category] = news
    }

    //todo implement LocalDataSource
    override fun saveNews(newsItem: NewsItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        private val TAG = "NewsRepository"

        private var INSTANCE: NewsRepository? = null

        @JvmStatic
        fun getInstance(
            newsRemoteDataSource: NewsDataSource<RssResponse>,
            newsLocalDataSource: NewsDataSource<List<NewsItem>>
        ) = INSTANCE
            ?: synchronized(NewsRepository::class.java) {
                INSTANCE ?: NewsRepository(newsRemoteDataSource, newsLocalDataSource).also {
                    INSTANCE = it
                }
            }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}