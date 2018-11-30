package com.rssnews.ua

import android.view.ViewGroup
import com.rssnews.R
import com.rssnews.data.NewsListItem
import com.rssnews.ua.base.BaseHolder
import kotlinx.android.synthetic.main.news_row.*

/**
 * Created by Vladyslav Ulianytskyi on 29.11.2018.
 */

class NewsHolder(recyclerView: ViewGroup) : BaseHolder<NewsListItem>(recyclerView, R.layout.news_row, intArrayOf()) {
    override fun bindItem(model: NewsListItem) {
        title.text = model.title
        author.text = model.author
        pubDate.text = model.date
        description.text = model.description
    }
}