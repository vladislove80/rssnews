package com.rssnews.ua.categories

import android.view.ViewGroup
import com.rssnews.data.Category
import com.rssnews.ua.base.BaseAdapter
import com.rssnews.ua.base.BaseHolder

/**
 * Created by Vladyslav Ulianytskyi on 29.11.2018.
 */

class CategoriesAdapter(listener: BaseHolder.OnItemClickListener<Category>?) : BaseAdapter<Category>(listener) {
    override fun onCreateHolder(recyclerView: ViewGroup, viewType: Int): BaseHolder<Category> = CategoryHolder(recyclerView)

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