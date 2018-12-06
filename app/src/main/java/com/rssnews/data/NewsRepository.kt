package com.rssnews.data

import android.util.Log
import com.rssnews.data.api.RssItem
import com.rssnews.data.api.RssResponse
import com.rssnews.data.model.NewsItem
import com.rssnews.data.source.NewsDataSource
import com.rssnews.util.getImageDescription
import com.rssnews.util.getImageSrcFromHTML
import com.rssnews.util.getNewsDescription

/**
 * Created by Vladyslav Ulianytskyi on 04.12.2018.
 */
class NewsRepository(
    val newsRemoteDataSource: NewsDataSource<RssResponse>,
    val newsLocalDataSource: NewsDataSource<List<NewsItem>>
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
        newsRemoteDataSource.getNews(category, link, object : NewsDataSource.LoadNewsCallback<RssResponse> {
            override fun onNewsLoaded(news: RssResponse) {
                callback.onNewsLoaded(parseResponse(news).apply {
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
        newsLocalDataSource.getNews(category, link, callback)
    }


    //todo implement LocalDataSource
    private fun refreshLocalDataSource(category: String, newsItem: List<NewsItem>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun refreshCache(category: String, news: List<NewsItem>) {
        cachedNews[category]?.let {
            if (it.isNotEmpty()) it.toMutableList().clear()
        }
        cachedNews[category] = news
    }

    //todo implement LocalDataSource
    override fun saveNews(newsItem: NewsItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun parseResponse(response: RssResponse?): MutableList<NewsItem> {
        return ArrayList<NewsItem>().apply {
            response?.rssChannel?.rssItems?.forEach { this.add(creteItem(it)) }
        }
    }

    private fun creteItem(it: RssItem): NewsItem {
        return NewsItem(
            it.title,
            it.pubDate,
            it.author,
            it.link,
            imageLink = getImageSrcFromHTML(it.description),
            imageDescription = getImageDescription(it.description),
            description = getNewsDescription(it.description)
        )
    }

    companion object {
        private val TAG = "NewsRepository"

        private var INSTANCE: NewsRepository? = null

        @JvmStatic
        fun getInstance(
            newsRemoteDataSource: NewsDataSource<RssResponse>,
            newsLocalDataSource: NewsDataSource<List<NewsItem>>
        ) = INSTANCE ?: synchronized(NewsRepository::class.java) {
            INSTANCE ?: NewsRepository(
                newsRemoteDataSource,
                newsLocalDataSource
            ).also {
                INSTANCE = it
            }
        }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}