package com.rssnews.ua.fragment

import android.view.ViewGroup
import com.rssnews.data.model.Category
import com.rssnews.ua.fragment.base.BaseAdapter
import com.rssnews.ua.fragment.base.BaseHolder

/**
 * Created by Vladyslav Ulianytskyi on 29.11.2018.
 */

class NewsCategoriesAdapter(listener: BaseHolder.OnItemClickListener<Category>?) : BaseAdapter<Category>(listener) {
    override fun onCreateHolder(recyclerView: ViewGroup, viewType: Int): BaseHolder<Category> =
        NewsCategoryHolder(recyclerView)

    fun setSelected(model: Category) {
        items.forEachIndexed { index, category ->
            if (model == category) {
                if (!category.isSelected) {
                    category.isSelected = true
                    notifyItemChanged(index)
                }
            } else if (category.isSelected) {
                category.isSelected = false
                notifyItemChanged(index)
            }
        }
    }
}