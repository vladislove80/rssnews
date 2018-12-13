package com.rssnews.ui.fragment.news.category

import android.view.ViewGroup
import com.rssnews.data.model.Category
import com.rssnews.ui.fragment.base.BaseAdapter
import com.rssnews.ui.fragment.base.BaseHolder

/**
 * Created by Vladyslav Ulianytskyi on 29.11.2018.
 */

class NewsCategoriesAdapter(listener: BaseHolder.OnItemClickListener<Category>?) : BaseAdapter<Category>(listener) {

    override fun onCreateHolder(recyclerView: ViewGroup, viewType: Int): BaseHolder<Category> = NewsCategoryHolder(recyclerView)

    fun setSelection(model: Category) {
        removeSelectFromOldItem()
        selectNewItem(model)
    }

    private fun removeSelectFromOldItem() {
        items.find { it.isSelected }?.let {
            it.isSelected = false
            notifyItemChanged(items.indexOf(it))
        }
    }

    private fun selectNewItem(model: Category) {
        model.apply {
            this.isSelected = !this.isSelected
            notifyItemChanged(items.indexOf(this))
        }
    }
}