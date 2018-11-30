package com.rssnews.ua.sport

import android.os.Bundle
import com.rssnews.data.Categories
import com.rssnews.ua.base.BaseFragment
import com.rssnews.ua.base.categoriesKey

/**
 * Created by Vladyslav Ulianytskyi on 29.11.2018.
 */

class SportNewsFragment : BaseFragment() {

    companion object {
        private val TAG = SportNewsFragment::class.java.simpleName
        fun newInstance(categories: Categories) = SportNewsFragment().apply {
            arguments = Bundle().apply { putParcelable(categoriesKey, categories) }
        }
    }
}