package com.rssnews.data

import com.rssnews.data.api.RssItem
import com.rssnews.data.api.RssResponse
import com.rssnews.data.model.NewsItem
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