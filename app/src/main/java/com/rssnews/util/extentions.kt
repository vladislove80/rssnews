package com.rssnews.util

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.rssnews.ui.viewmodel.ViewModelFactory


/**
 * Created by Vladyslav Ulianytskyi on 28.11.2018.
 */

fun <T : ViewModel> Fragment.obtainViewModel(viewModelClass: Class<T>) = ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(viewModelClass)

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}
