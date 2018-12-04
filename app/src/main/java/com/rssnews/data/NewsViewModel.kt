package com.rssnews.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.rssnews.data.api.CBCApi
import com.rssnews.data.api.RssItem
import com.rssnews.data.api.RssResponse
import com.rssnews.data.model.NewsListItem
import com.rssnews.ua.base.BaseFragment
import com.rssnews.util.getImageDescription
import com.rssnews.util.getImageSrcFromHTML
import com.rssnews.util.getNewsDescription
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Vladyslav Ulianytskyi on 30.11.2018.
 */
class NewsViewModel : ViewModel() {
    private val baseURL = "https://rss.cbc.ca/"

    val newsLiveData: MutableLiveData<MutableList<NewsListItem>> = MutableLiveData()
    val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()

    fun getNews(link: String) = CBCApi.create(baseURL).getRssData(link).enqueue(object : Callback<RssResponse> {
        override fun onFailure(call: Call<RssResponse>, t: Throwable) {
            errorLiveData.postValue(t)
        }

        override fun onResponse(call: Call<RssResponse>, response: Response<RssResponse>) {
            newsLiveData.postValue(parseResponse(response.body()))
        }
    })

    companion object {
        fun of(frag: BaseFragment) = ViewModelProviders.of(frag).get(NewsViewModel::class.java)
    }

    fun parseResponse(response: RssResponse?): MutableList<NewsListItem> {
        return ArrayList<NewsListItem>().apply {
            response?.rssChannel?.rssItems?.forEach { this.add(creteItem(it)) }
        }
    }

    private fun creteItem(it: RssItem): NewsListItem {
        return NewsListItem(
            it.title,
            it.pubDate,
            it.author,
            it.link,
            imageLink = getImageSrcFromHTML(it.description),
            imageDescription = getImageDescription(it.description),
            description = getNewsDescription(it.description)
        )
    }
}