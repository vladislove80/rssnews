package com.rssnews.data.source

import com.rssnews.data.model.NewsItem

/**
 * Created by Vladyslav Ulianytskyi on 05.12.2018.
 */
object DataSourceImpl: DataSource<List<NewsItem>> {
    override fun getNews(category: String, link: String, callback: DataSource.Callback<List<NewsItem>>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveNews(newsItem: NewsItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}