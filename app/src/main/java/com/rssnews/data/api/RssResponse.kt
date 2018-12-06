package com.rssnews.data.api

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

/**
 * Created by Vladyslav Ulianytskyi on 30.11.2018.
 */

@Root(name = "rss", strict = false)
class RssResponse {
    @set:Element(name = "channel")
    @get:Element(name = "channel")
    var rssChannel = RssChannel()
}

@Root(name = "channel", strict = false)
class RssChannel {
    @set:ElementList(name = "item", inline = true, required = false)
    @get:ElementList(name = "item", inline = true, required = false)
    var rssItems: List<RssItem> = ArrayList()
}

@Root(name = "item", strict = false)
class RssItem {
    @set:Element(name = "title")
    @get:Element(name = "title")
    var title = ""
    @set:Element(name = "link")
    @get:Element(name = "link")
    var link = ""
    @set:Element(name = "pubDate")
    @get:Element(name = "pubDate")
    var pubDate = ""
    @set:Element(name = "author", required = false)
    @get:Element(name = "author", required = false)
    var author = ""
    @set:Element(name = "description", required = false)
    @get:Element(name = "description", required = false)
    var description: String = ""
}
