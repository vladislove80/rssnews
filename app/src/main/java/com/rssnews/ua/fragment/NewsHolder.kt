package com.rssnews.ua.fragment

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.rssnews.R
import com.rssnews.data.model.NewsItem
import com.rssnews.ua.fragment.base.BaseHolder
import kotlinx.android.synthetic.main.news_row.*

/**
 * Created by Vladyslav Ulianytskyi on 29.11.2018.
 */

class NewsHolder(recyclerView: ViewGroup) : BaseHolder<NewsItem>(recyclerView, R.layout.news_row, intArrayOf()) {
    override fun bindItem(model: NewsItem) {
        val subtitle = model.author + " " + model.date
        val apply = RequestOptions().apply {
            placeholder(R.drawable.placeholder)
            error(R.drawable.placeholder)
            diskCacheStrategy(DiskCacheStrategy.ALL)
            priority(Priority.HIGH)
        }
        tvTitle.text = model.title
        tvAuthorAndDate.text = subtitle
        iv.apply {
            Glide.with(context)
                .load(model.imageLink)
                .apply(apply)
                .into(this)
        }
        tvImageDescription.text = model.imageDescription
        tvDescription.text = model.description
    }
}