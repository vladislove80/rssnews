package com.rssnews.ua.fragment

import android.view.ViewGroup
import com.rssnews.data.model.NewsItem
import com.rssnews.ua.fragment.base.BaseAdapter
import com.rssnews.ua.fragment.base.BaseHolder

/**
 * Created by Vladyslav Ulianytskyi on 29.11.2018.
 */

class NewsAdapter(listener: BaseHolder.OnItemClickListener<NewsItem>?) : BaseAdapter<NewsItem>(listener) {
    override fun onCreateHolder(recyclerView: ViewGroup, viewType: Int): BaseHolder<NewsItem> =
        NewsHolder(recyclerView)
}