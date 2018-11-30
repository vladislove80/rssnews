package com.rssnews.ua.general

import android.os.Bundle
import com.rssnews.data.Categories
import com.rssnews.ua.base.BaseFragment
import com.rssnews.ua.base.categoriesKey

/**
 * Created by Vladyslav Ulianytskyi on 29.11.2018.
 */
class GeneralNewsFragment : BaseFragment() {

    companion object {
        private val TAG = GeneralNewsFragment::class.java.simpleName

        fun newInstance(categories: Categories) = GeneralNewsFragment().apply {
            arguments = Bundle().apply { putParcelable(categoriesKey, categories) }
        }
    }
}