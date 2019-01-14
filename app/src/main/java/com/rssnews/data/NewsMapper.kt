package com.rssnews.data

import com.rssnews.data.api.RssItem
import com.rssnews.data.api.RssResponse
import com.rssnews.data.model.NewsItem
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

/**
 * Created by Vladyslav Ulianytskyi on 07.12.2018.
 */
class NewsMapper {
    companion object {
        fun rssResponseToItems(response: RssResponse?): MutableList<NewsItem> {
            return ArrayList<NewsItem>().apply {
                response?.rssChannel?.rssItems?.forEach { this.add(creteItem(it)) }
            }
        }

        private fun creteItem(rssItem: RssItem): NewsItem {
            return NewsItem(
                title = rssItem.title,
                date = getDate(rssItem.pubDate),
                author = rssItem.author,
                newsLink = rssItem.link,
                imageLink = getImageSrcFromHTML(rssItem.description),
                imageDescription = getImageDescription(rssItem.description),
                description = getNewsDescription(rssItem.description)
            )
        }

        private fun getDate(pubDate: String): String {
            val simpleDateFormat = SimpleDateFormat("EEE, d MMM yyyy HH:mm", Locale.ENGLISH)
            return simpleDateFormat.format(simpleDateFormat.parse(pubDate))
        }

        private fun getImageSrcFromHTML(html: String): String {
            Pattern.compile("<img[^>]*src=[\\\"']([^\\\"^']*)").matcher(html).apply {
                return if (this.find()) {
                    val src = this.group()
                    src.substring(src.indexOf("src=") + 5, src.length)
                } else ""
            }
        }

        private fun getImageDescription(htmlDescription: String): String {
            Pattern.compile("<img[^>]*title=[\\\"']([^\\\"^']*)").matcher(htmlDescription).apply {
                return if (this.find()) {
                    val src = this.group()
                    src.substring(src.indexOf("title=") + 7, src.length)
                        .replace("&#39;", "\'")
                        .trim()
                } else ""
            }
        }

        private fun getNewsDescription(htmlDesc: String): String {
            return htmlDesc.substring(htmlDesc.indexOf("<p>") + 3, htmlDesc.indexOf("</p>")).trim()
        }
    }
}