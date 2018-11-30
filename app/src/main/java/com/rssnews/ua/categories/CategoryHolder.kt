package com.rssnews.ua.categories

import android.util.Log
import android.view.ViewGroup
import com.rssnews.R
import com.rssnews.data.Category
import com.rssnews.ua.base.BaseHolder
import kotlinx.android.synthetic.main.category.*

/**
 * Created by Vladyslav Ulianytskyi on 29.11.2018.
 */
class CategoryHolder(recyclerView: ViewGroup) : BaseHolder<Category>(recyclerView, R.layout.category, intArrayOf(R.id.tvCategory)) {

    override fun bindItem(model: Category) {
        tvCategory.text = model.categoryName
        if (model.isSelected) {
            Log.d("CategoryHolder", "model.categoryName ${model.categoryName}, model.isSelected ${model.isSelected}")
            tvCategory.chipBackgroundColor = getColorStateList(R.color.chipSelectedIconTint)
        } else {
            tvCategory.chipBackgroundColor = getColorStateList(R.color.chipIconTint)
        }
    }
}
