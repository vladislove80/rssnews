package com.rssnews.ua.regional

import android.os.Bundle
import com.rssnews.data.model.Categories
import com.rssnews.ua.base.BaseFragment
import com.rssnews.ua.base.categoriesKey

/**
 * Created by Vladyslav Ulianytskyi on 29.11.2018.
 */

class RegionalNewsFragment : BaseFragment() {

    companion object {
        private val TAG = RegionalNewsFragment::class.java.simpleName
        fun newInstance(categories: Categories) = RegionalNewsFragment().apply {
            arguments = Bundle().apply { putParcelable(categoriesKey, categories) }
        }
    }
}