package com.rssnews.ua

import android.view.ViewGroup
import com.rssnews.data.model.NewsListItem
import com.rssnews.ua.base.BaseAdapter
import com.rssnews.ua.base.BaseHolder

/**
 * Created by Vladyslav Ulianytskyi on 29.11.2018.
 */

class NewsAdapter(listener: BaseHolder.OnItemClickListener<NewsListItem>?) : BaseAdapter<NewsListItem>(listener) {
    override fun onCreateHolder(recyclerView: ViewGroup, viewType: Int): BaseHolder<NewsListItem> =
        NewsHolder(recyclerView)
}