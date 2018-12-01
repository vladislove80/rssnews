package com.rssnews.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.rssnews.data.api.CBCApi
import com.rssnews.data.api.RssResponse
import com.rssnews.ua.base.BaseFragment
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
        val list = ArrayList<NewsListItem>()
        response?.rssChannel?.rssItems?.forEach {
            list.add(NewsListItem(it.title, it.pubDate, it.author, it.link, description = it.description))
        }
        return list
    }
}