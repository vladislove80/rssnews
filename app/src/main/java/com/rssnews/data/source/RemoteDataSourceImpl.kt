package com.rssnews.data.source

import com.rssnews.data.api.CBCApi
import com.rssnews.data.api.RssResponse
import com.rssnews.data.model.Category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Vladyslav Ulianytskyi on 05.12.2018.
 */
object RemoteDataSourceImpl : RemoteDataSource<RssResponse> {
    private const val baseURL = "https://rss.cbc.ca/"

    override fun getNews(category: String, link: String, callback: RemoteDataSource.Callback<RssResponse>) {
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

    val  general = mutableListOf(
            Category("Top Stories", "lineup/topstories.xml", true),
            Category("World", "lineup/world.xml"),
            Category("Canada", "lineup/canada.xml"),
            Category("Politics", "lineup/politics.xml"),
            Category("Business", "lineup/business.xml"),
            Category("Health", "lineup/health.xml"),
            Category("Arts & Entertainment", "lineup/arts.xml"),
            Category("Technology & Science", "lineup/technology.xml"),
            Category("Offbeat", "lineup/offbeat.xml"),
            Category("Aboriginal", "https://www.cbc.ca/cmlink/rss-cbcaboriginal")
    )

    val sport = mutableListOf(
            Category("Sports", "lineup/sports.xml", true),
            Category("MLB", "lineup/sports-mlb.xml"),
            Category("NBA", "lineup/sports-nba.xml"),
            Category("Curling", "lineup/sports-curling.xml"),
            Category("CFL", "lineup/sports-cfl.xml"),
            Category("NFL", "lineup/sports-nfl.xml"),
            Category("lNHL", "lineup/sports-nhl.xml"),
            Category("Soccer", "lineup/sports-soccer.xml"),
            Category("Figure Skating", "lineup/sports-figureskating.xml")
    )

    val regional = mutableListOf(
            Category("British Columbia", "lineup/canada-britishcolumbia.xml", true),
            Category("Kamloops", "lineup/canada-kamloops.xml"),
            Category("Calgary", "lineup/canada-calgary.xml"),
            Category("Edmonton", "lineup/canada-edmonton.xml"),
            Category("Saskatchewan", "lineup/canada-saskatchewan.xml"),
            Category("Saskatoon", "lineup/canada-saskatoon.xml"),
            Category("Manitoba", "lineup/canada-manitoba.xml"),
            Category("Thunder Bay", "lineup/canada-thunderbay.xml"),
            Category("Sudbury", "lineup/canada-sudbury.xml"),
            Category("Windsor", "lineup/canada-windsor.xml"),
            Category("London", "https://www.cbc.ca/cmlink/rss-canada-london"),
            Category("Kitchener-Waterloo", "lineup/canada-kitchenerwaterloo.xml"),
            Category("Toronto", "lineup/canada-toronto.xml"),
            Category("Hamilton", "lineup/canada-hamiltonnews.xml"),
            Category("Montreal", "http://rss.cbc.ca/lineup/canada-montreal.xml"),
            Category("New Brunswick", "lineup/canada-newbrunswick.xml"),
            Category("Prince Edward Island", "lineup/canada-pei.xml"),
            Category("Nova Scotia", "lineup/canada-novascotia.xml"),
            Category("Newfoundland & Labrador", "lineup/canada-newfoundland.xml"),
            Category("North", "lineup/canada-north.xml")
    )
}