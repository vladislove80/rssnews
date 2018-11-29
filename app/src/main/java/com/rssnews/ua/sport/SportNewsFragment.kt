package com.rssnews.ua.sport

import android.os.Bundle
import android.view.View
import com.rssnews.ua.base.BaseFragment

/**
 * Created by Vladyslav Ulianytskyi on 29.11.2018.
 */

class SportNewsFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showRetry()

    }

    companion object {
        private val tag = SportNewsFragment::class.java.simpleName
        fun newInstance(): SportNewsFragment {
            return SportNewsFragment()
        }
    }
}