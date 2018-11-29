package com.rssnews.ua.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rssnews.R
import com.rssnews.util.gone
import com.rssnews.util.visible
import kotlinx.android.synthetic.main.fragment_news.*

/**
 * Created by Vladyslav Ulianytskyi on 28.11.2018.
 */

open class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRetry.setOnClickListener {
            //todo implement retry get data
        }
    }

    fun showProgress() {
        recyclerView.gone()
        btnRetry.gone()
        progressBar.visible()
    }

    fun showContent() {
        recyclerView.visible()
        btnRetry.gone()
        progressBar.gone()
    }

    fun showRetry() {
        recyclerView.gone()
        btnRetry.visible()
        progressBar.gone()
    }
}