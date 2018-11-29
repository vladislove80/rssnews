package com.rssnews.ua.general

import android.os.Bundle
import android.view.View
import com.rssnews.ua.base.BaseFragment

/**
 * Created by Vladyslav Ulianytskyi on 29.11.2018.
 */
class GeneralNewsFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProgress()

    }

    companion object {
        private val tag = GeneralNewsFragment::class.java.simpleName
        fun newInstance(): GeneralNewsFragment {
            return GeneralNewsFragment()
        }
    }
}