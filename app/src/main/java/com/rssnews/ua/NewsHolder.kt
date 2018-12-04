package com.rssnews.ua

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.rssnews.R
import com.rssnews.data.model.NewsItem
import com.rssnews.ua.base.BaseHolder
import kotlinx.android.synthetic.main.news_row.*

/**
 * Created by Vladyslav Ulianytskyi on 29.11.2018.
 */

class NewsHolder(recyclerView: ViewGroup) : BaseHolder<NewsItem>(recyclerView, R.layout.news_row, intArrayOf()) {
    override fun bindItem(model: NewsItem) {
        tvTitle.text = model.title
        val s = model.author + "" + model.date
        tvAuthorAndDate.text = s
        Glide.with(context).load(model.imageLink).into(iv)
        tvImageDescription.text = model.imageDescription
        tvDescription.text = model.description
    }
}