package com.rssnews.ua.regional

import android.os.Bundle
import android.view.View
import com.rssnews.ua.base.BaseFragment

/**
 * Created by Vladyslav Ulianytskyi on 29.11.2018.
 */

class RegionalNewsFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showContent()
    }

    companion object {
        private val tag = RegionalNewsFragment::class.java.simpleName
        fun newInstance(): RegionalNewsFragment {
            return RegionalNewsFragment()
        }
    }
}